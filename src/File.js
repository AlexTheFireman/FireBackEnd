import React, {useState} from "react";

export default function File() {
    const [selectedFile, setSelectedFile] = useState('');
    const [isFileSelected, setIsFileSelected] = useState(false);
    const [answer, setAnswer] = useState('');

    const fileSelectedHandler = event => {
        setSelectedFile(event.target.files[0]);
        setIsFileSelected(true)
    };

     function getValue(answer) {
        switch (answer) {
            case 'SUCCESS':
                return "Файл успешно загружен";
            case 'CHECK_FILE_EXTENSION':
                return "Система принимает файлы исключительно с расширением \"xls\" или \"xlsx\". " +
                    "Пожалуйста, проверьте расширение файла!";
            case 'FILE_ALREADY_EXISTS':
                return "Файл с таким именем уже содержится в базе данных";
            default:
                return "Файл не выбран";
        }
    }

    const fileUploadHandler = () => {
        if (isFileSelected === true) {
            const formData = new FormData();
            formData.append("file", selectedFile);
            fetch("/api/upload", {
                method: 'POST',
                body: formData
            })
                .then(response => response.text())
                .then(response => setAnswer(response))

        } else {
            setAnswer('NO');
        }
    };

    return (
          <div align="center">
              <table>
                  <tr>
                      <td>Выберите файл:</td>
                      <td><input type="file" onChange={fileSelectedHandler}/></td>
                  </tr>
                  <tr>
                      <td></td>
                      <td>
                          <button onClick={fileUploadHandler}>Загрузить</button>
                      </td>
                  </tr>
              </table>
              <td>
                  <h1>{getValue(answer)}</h1>
              </td>
          </div>
    );
}







