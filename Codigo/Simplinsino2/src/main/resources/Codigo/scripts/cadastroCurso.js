function listarCursos() {

    fetch("http://localhost:6800/getCursos")
        .then((response) => response.json())
        .then((cursos) => {
            if (cursos.length > 0) {
                let lista = document.getElementById("lista")

                let htmlStr = ``
                for (i = 0; i < cursos.length; i++) {
                    htmlStr +=
                        `<tr>
                            <th scope="row" class="id_curso">${cursos[i].id_curso}</th>
                            <td>${cursos[i].curso_nome}</td>
                            <td>${db.disciplinas[cursos[i].id_disciplinafk].titulo}</td>
                            <td><button type="button" id="addVideos"><a href="cadastroVideos.html?id=${cursos[i].id_curso}">+ vídeos</a></button></td>
                        </tr>`
                }

                lista.innerHTML = htmlStr
            }
        })
}


// Preenche o formulário quando o usuario clicar em uma linha da tabela
function init() {

    // ---------------------------------------------------------------------------- //
    // INSERT

    $("#btnInsert").click(function () {

        // Obtem os valores dos campos do formulário


        let campoID;
        let campoNome = $("#inputNome").val();
        let campoDuracao = $("#inputDuracao").val();
        let campoDisciplina = $("#inputDisciplina").val();
        let campoDescricao = $("#inputDescricao").val();
        let campoImagem = encodeURIComponent($("#inputImagem").val());


    

        fetch(`http://localhost:6800/getUltimoID`)
            .then((response) => response.json())
            .then((ultimoID) => {
                campoID = ultimoID + 1;
                fetch(`http://localhost:6800/insert/${campoDisciplina}/${campoID}/${campoNome}/${campoDuracao}/${campoDescricao}/${campoImagem}`)
                    .then((response) => response.json())
                    .then((data) => {
                        alert("Curso inserido com sucesso!");
                        // Reexibe os tarefas
                        location.reload();
                    })

                    .catch((error) => alert("Erro ao inserir"));

            })



        // Limpa o formulario
        $("#form-curso")[0].reset();
    });


    // ================= UPDATE
    $("#btnUpdate").click(function () {
        // Obtem os valores dos campos do formulário
        let campoID = $("#inputId").val();
        let campoNome = $("#inputNome").val();
        let campoDuracao = $("#inputDuracao").val();
        let campoDisciplina = $("#inputDisciplina").val();
        let campoDescricao = $("#inputDescricao").val();
        let campoImagem = encodeURIComponent($("#inputImagem").val());

        fetch(`http://localhost:6800/update/${campoDisciplina}/${campoID}/${campoNome}/${campoDuracao}/${campoDescricao}/${campoImagem}`)
            .then((response) => response.json())
            .then((data) => {
                alert("Curso inserido com sucesso!");
                // Reexibe os tarefas
                location.reload();
            })
            .catch((error) => alert("Erro ao atualizar Curso"));

        // Limpa o formulario
        $("#form-curso")[0].reset();
    });


    //============ DELETE 

    // Intercepta o click do botão Excluir
    $("#btnDelete").click(function () {
        let campoID = $("#inputId").val();
        if (campoID == "") {
            displayMessage("Selecione uma tarefa a ser excluída.");
            return;
        }

        fetch(`http://localhost:6800/remove/${campoID}`)
            .then((response) => response.json())
            .then((data) => {
                alert("Curso removido com sucesso!");
                // Reexibe os tarefas
                location.reload();
            })
            .catch((error) => alert("Erro ao remover Curso"));

        // Limpa o formulario
        $("#form-curso")[0].reset();
    });


    $("#lista").on("click", "tr", function (e) {

        let lista = this;
        let id = lista.querySelector(".id_curso").innerText

        fetch(`http://localhost:6800/search/${id}`)
            .then((response) => response.json())
            .then((curso) => {
                if (curso.length > 0) {
                    $("#inputId").val(id);
                    $("#inputNome").val(curso[0].curso_nome);
                    $("#inputDuracao").val(curso[0].curso_duracao);
                    $("#inputDisciplina").val(curso[0].id_disciplinafk);
                    $("#inputDescricao").val(curso[0].curso_descricao);
                    $("#inputImagem").val(curso[0].curso_imagem);
                }
            })
    });
}


