import { Status, Todo } from "./model";
import './TodoItem.css'

interface TodoItemProps {
    todo: Todo
    onTodoDeletion: () => void;
    onTodoChange: (todos: Array<Todo>) => void;
}

export default function TodoItem(props: TodoItemProps) {

    const deleteTodo = () => {
        fetch(`http://localhost:8080/todos/${props.todo.id}`, {
            method: 'DELETE'
        })
        .then(() => props.onTodoDeletion());
    };

    const toggle = () => {
        const newStatus = props.todo.status === Status.Open ? Status.Done : Status.Open;

        fetch(`http://localhost:8080/todos/${props.todo.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.todo.id,
                task: props.todo.task,
                description: props.todo.description,
                status: newStatus
            })
        })
        .then(response => response.json())
        .then((todosFromBackend: Array<Todo>) => props.onTodoChange(todosFromBackend));
    }

    return (
        <div>
            <div className={props.todo.status === Status.Done ? 'selected': ''} onClick={toggle}>{props.todo.task} - {props.todo.description}</div> <button onClick={deleteTodo}>Löschen</button>
        </div>
    )
}