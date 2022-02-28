import { useState } from "react"
import { Todo } from "./model";

interface TodoFromProps {
    onTodoCreation: (todos: Array<Todo>) => void;
}

export default function TodoForm(props: TodoFromProps) {

    const [task, setTask] = useState('');
    const [description, setDescription] = useState('');

    const addTask = () => {
        fetch('http://localhost:8080/todos', {
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
        .then((todosFromBackend: Array<Todo>) => props.onTodoCreation(todosFromBackend))
    }

    return (
        <div>
            <input type="text" placeholder="Aufgabe" value={task} onChange={ev => setTask(ev.target.value)} />
            <input type="text" placeholder="Beschreibung" value={description} onChange={ev => setDescription(ev.target.value)} />
            <button onClick={addTask}>Senden</button>
        </div>
    )
}