import { useEffect, useState } from "react"
import { Todo } from "./model";
import TodoForm from "./TodoForm";
import TodoItem from "./TodoItem";
import './TodoList.css';
import { useTranslation } from "react-i18next";


export default function TodoList() {
    const [todos, setTodos] = useState([] as Array<Todo>);
    const { t } = useTranslation();

    const fetchAll = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`)
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>)  => setTodos(todosFromBackend));
    }

    const deleteChecked = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`, { method: 'DELETE' })
            .then(fetchAll)
    }
    useEffect(() => {
        fetchAll();
    }, []);
    return (
        <div className="todo-list">
            <div>
                <TodoForm onTodoCreation={fetchAll} />
            </div>
            <div>
            <button onClick={deleteChecked}>{t('Delete all Tasks')}</button>
            </div>
            <ul>
                {todos.map(todo => <li key={todo.id}><TodoItem todo={todo} onTodoDeletion={fetchAll} onTodoChange={setTodos} /></li>)}
            </ul>
        </div>
    )
}