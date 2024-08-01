import "./app.css";
import { useState } from "react";
import { IForm } from "./Types/Form";

const App = () => {

  const [ form, setForm ] = useState<IForm>({
    name : "",
    address : ""
  });

  const [ errors, setErrors ] = useState({
    name : false,
    addres : false
  });

  const [ students, setStudents ] = useState<IForm[]>([]);

  const onSubmit = (event:React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if(errors.name || errors.addres) {
      alert("Please fill empty fields");
      return;
    }
    fetch("http://localhost:8080/api/student/v1/add", {
      method: "POST",
      headers: {
        "Content-Type":"application/json"
      },
      body: JSON.stringify(form)
    }).then(() => {
      console.log("New student added");
    }).catch(e => {
      console.log("Error, couldn't fetch to database", e);
    });

    alert("Form submitted");
  }
  
  const handleErrors = () => {
    errors.name = (form.name === "");
    errors.addres = (form.address === "");
    setErrors(errors);
  }

  const fetchData = () => {
    fetch("http://localhost:8080/api/student/v1/getAll")
    .then(res => res.json())
    .then(result => {
      setStudents(result);
    })
    .catch(e => {
      console.log("Not connected to the database", e);
      setStudents([]);
    })
  }

  return (
    <div className="App">
        <div className="App--header">
          <h1>Spring Boot API</h1>
          <button onClick={fetchData}>Get data</button>
        </div>
        <div className="App--form">
          <form action="" method="post" onSubmit={onSubmit}>
            <fieldset>
              <legend>Students simple form</legend>
              <div className="App--form__input">
                <label htmlFor="">Name: </label>
                <input type="text" name="" id="name" onChange={e => setForm(f => ({...f, name:e.target.value }))}/><br/>
              </div>
              <div className="App--form__input">
                <label htmlFor="">Address: </label>
                <input type="text" name="" id="address" onChange={e => setForm(f => ({...f, address:e.target.value }))}/><br/>
              </div>
              <button type="submit" onClick={handleErrors}>Submit</button>
            </fieldset>
          </form>
        </div>
        <div className="App--student">
          <h1 style={{ paddingBottom:15 }}>Students in database</h1>
          {
            students.map((student, index) => (
            <div key={index} className="App--student__item">
              <h1>{student.name}</h1>
              <h3>{student.address}</h3>
            </div>
            ))
          }
        </div>
    </div>
  );
}

export default App;


