import { useState } from "react"
import { Todo } from "./model";
import './TodoForm.css';
import { useTranslation } from "react-i18next";


interface TodoFromProps {
    onTodoCreation: (todos: Array<Todo>) => void;
}
export default function TodoForm(props: TodoFromProps) {
    const [task, setTask] = useState('');
    const [description, setDescription] = useState('');
    const {t} = useTranslation();

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
        .then(response => {
            if (response.status === 200){
             return response.json()}
            throw new Error('Statuscode:' + response.status)
            })
        .then((todosFromBackend: Array<Todo>) => {
            setTask('');
            setDescription('');
            props.onTodoCreation(todosFromBackend);
        });
    }
    return (
        <div>
            <input type="text" placeholder={t('task')} value={task} onChange={ev => setTask(ev.target.value)} />
            <input type="text" placeholder={t('description')} value={description} onChange={ev => setDescription(ev.target.value)} className="description-field" />
            <button data-testid="add-button" onClick={addTask} className="send-button">{t('send')}</button>
        </div>
    )
} 