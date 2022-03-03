import { useState } from "react"
import { Todo } from "./model";
import './TodoForm.css';
interface TodoFromProps {
    onTodoCreation: (todos: Array<Todo>) => void;
}
export default function TodoForm(props: TodoFromProps) {
    const [task, setTask] = useState('');
    const [description, setDescription] = useState('');

    const addTask = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                task: task,
                description: description
            })
        })
        .then(response => response.json())
        .then((todosFromBackend: Array<Todo>) => {
            setTask('');
            setDescription('');
            props.onTodoCreation(todosFromBackend);
        });
    }
    return (
        <div>
            <input type="text" placeholder="Aufgabe" value={task} onChange={ev => setTask(ev.target.value)} />
            <input type="text" placeholder="Beschreibung" value={description} onChange={ev => setDescription(ev.target.value)} className="description-field" />
            <button data-testid="add-button" onClick={addTask} className="send-button">Senden</button>
        </div>
    )
} 