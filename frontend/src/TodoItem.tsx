import { useState } from "react";
import { useTranslation } from "react-i18next";
import { Status, Todo } from "./model";
import './TodoItem.css'

interface TodoItemProps {
    todo: Todo
    onTodoDeletion: () => void;
    onTodoChange: (todos: Array<Todo>) => void;
}
export default function TodoItem(props: TodoItemProps) {

    const [taskToEdit, setTaskToEdit] = useState(props.todo.task);
    const [descriptionToEdit, setDescriptionToEdit] = useState(props.todo.description);
    const [editMode, setEditMode] = useState(false);

    const { t } = useTranslation();

    const deleteTodo = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${props.todo.id}`, {
            method: 'DELETE'
        })
            .then(() => props.onTodoDeletion());
    };

    const fetchToEdit = (todo: Todo) => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${props.todo.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(todo)
        })
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => {
                props.onTodoChange(todosFromBackend);
                setEditMode(false);
            });
    }

    const editTodo = () => {
        fetchToEdit({
            id: props.todo.id,
            task: taskToEdit,
            description: descriptionToEdit,
            status: props.todo.status
        });
    }

    const toggle = () => {
        const newStatus = props.todo.status === Status.Open ? Status.Done : Status.Open;
        fetchToEdit({
            id: props.todo.id,
            task: props.todo.task,
            description: props.todo.description,
            status: newStatus
        });
    }

    return (
        <div>
            {
                editMode
                    ?
                    <div>
                        <input type="text"
                               value={taskToEdit}
                               onChange={ev => setTaskToEdit(ev.target.value)}
                               onKeyUp={ev => {if (ev.keyCode === 13) { editTodo(); }}} />
                        <input type="text"
                               value={descriptionToEdit}
                               onChange={ev => setDescriptionToEdit(ev.target.value)}
                               onKeyUp={ev => {if (ev.keyCode === 13) { editTodo(); }}} />
                        <button onClick={editTodo}>{t('send')}</button>
                    </div>
                    :
                    <div>
                    <span className={props.todo.status === Status.Done ? 'selected': ''}
                          onClick={toggle}>
                        {props.todo.task} - {props.todo.description}
                    </span>
                        <button onClick={() => setEditMode(true)}>Edit</button>
                        <button onClick={deleteTodo}>{t('delete')}</button>
                    </div>
            }
        </div>
    )
}