function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
        function (m, key, value) {
            vars[key] = value;
        });

    return vars;
}


function preecheCampoId_curso () {
    let campoId_curso =  document.getElementById('campoId_curso');
    let Id_curso = getUrlVars()["id"];
    htmlStr = ''
    fetch(`http://localhost:6800/search/${Id_curso}`)
        .then((response) => response.json())
        .then((cursos) => {
            for (i = 0; i < cursos.length; i++) {
                if (Id_curso == cursos[i].id_curso) {
                    htmlStr += 
                        `
                            <label for="inputCurso" class="form-label">Nome do curso</label>
                            <input type="text" class="form-control" id="inputCurso" placeholder="${cursos[i].curso_nome}" disabled>
                        `
                }
            }
        campoId_curso.innerHTML = htmlStr
        })
    .catch((error) => alert("Erro ao atualizar Pessoa"));    
}

function listarVideos () {

    fetch("http://localhost:6815/getVideos")
        .then((response) => response.json())
        .then((videos) =>{

            fetch("http://localhost:6800/getCursos")
                .then((response) => response.json())
                .then((cursos) =>{
                    if(videos.length > 0) {
                        let lista = document.getElementById("lista")
                    
                        let htmlStr = ``
                        for (i = 0; i < videos.length; i++) {
                            for (j = 0; j < cursos.length; j++) {
                                if (videos[i].id_curso == cursos[j].id_curso) {
                                    htmlStr += 
                                    `<tr>
                                        <th scope="row" class="id_video">${videos[i].id_video}</th>
                                        <td>${cursos[j].curso_nome}</td>
                                        <td>${videos[i].nome_video}</td>
                                        <td>${videos[i].link_video}</td>
                                    </tr>`
                                }
                            }
                        }
                    lista.innerHTML = htmlStr
                    
                    }  
                    
            })
        })
}

// Preenche o formulário quando o usuario clicar em uma linha da tabela
function init() {

    // ---------------------------------------------------------------------------- //
    // INSERT

    $("#btnInsert").click(function () {
        
        // Obtem os valores dos campos do formulário
        let campoID;
        let campoId_curso = getUrlVars()["id"];
        let campoNome = $("#inputNome").val();
        let campoDescricaoVideo = $("#inputDescricaoVideo").val();
        let campoLink = $("#inputLinkVideo").val();

        fetch(`http://localhost:6815/getUltimoID`)
        .then((response) => response.json())
        .then((ultimoID) => {
            campoID = ultimoID+1;
            fetch(`http://localhost:6815/insert/${campoID}/${campoId_curso}/${campoLink}/${campoDescricaoVideo}/${campoNome}`)
            .then((response) => response.json())
            .then((data) => {
                alert("Video inserido com sucesso!");
                // Reexibe os tarefas
                location.reload();
            })
        
        .catch((error) => alert("Erro ao inserir"));

        })


        

        // Limpa o formulario
        $("#form-video")[0].reset();
        
    });


    // ---------------------------------------------------------------------------- //
    //UPDATE

    $("#btnUpdate").click(function () {
        // Obtem os valores dos campos do formulário
        let campoID = $("#inputIdVideo").val();
        let campoId_curso = getUrlVars()["id"];
        let campoNome = $("#inputNome").val();
        let campoDescricaoVideo = $("#inputDescricaoVideo").val();
        let campoLink = $("#inputLinkVideo").val();
        
        fetch(`http://localhost:6815/update/${campoID}/${campoId_curso}/${campoLink}/${campoDescricaoVideo}/${campoNome}`)
            .then((response) => response.json())
            .then((data) => {
                alert("Video atualizado com sucesso!");
                // Reexibe os tarefas
        		location.reload();
            })
        .catch((error) => alert("Erro ao atualizar Video"));

        // Limpa o formulario
        $("#form-video")[0].reset();
    });

    // ---------------------------------------------------------------------------- //
    // DELETE 

    // Intercepta o click do botão Excluir
    $("#btnDelete").click(function () {
        let campoID = $("#inputIdVideo").val();
        if (campoID == "") {
            displayMessage("Selecione uma tarefa a ser excluída.");
            return;
        }

        fetch(`http://localhost:6815/remove/${campoID}`)
        .then((response) => response.json())
        .then((data) => {
            alert("Video removida com sucesso!");
            // Reexibe as tarefas
        	location.reload();
        })
        .catch((error) => alert("Erro ao remover Video"));

        // Limpa o formulario
        $("#form-video")[0].reset();
    });


    $("#lista").on("click", "tr", function (e) {
    
        let lista = this;
        let id = lista.querySelector(".id_video").innerText
    
        fetch(`http://localhost:6815/search/${id}`)
            .then((response) => response.json())
            .then((videos) =>{

                if(videos.length > 0) {
                    $("#inputIdVideo").val(id);
                    $("#inputCurso").val(videos[0].id_curso);
                    $("#inputNome").val(videos[0].nome_video);
                    $("#inputDescricaoVideo").val(videos[0].descricao_video);
                    $("#inputLinkVideo").val(videos[0].link_video);
                }
            })
    });
}


