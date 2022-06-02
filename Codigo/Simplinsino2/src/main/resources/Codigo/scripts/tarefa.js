function exibeTarefas() {
    // Remove todas as linhas do corpo da tabela
    $("#table-tarefas").html("");
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    if (!usuario) {
        return
    }

    fetch(`http://localhost:6795/getTarefas`)
        .then((response) => response.json())
        .then((tarefas) =>{
            if(tarefas.length > 0) {
                for (i = 0; i < tarefas.length; i++) {
                    if (tarefas[i].id_alunofk == usuario[0].id_aluno) {
                        $("#table-tarefas").append(`<li class="col-12 col-sm-12 col-md-4 col-lg-3">
                            <div class="container-tarefa h-100" id="container-${i}">
                                <p hidden class="id-tarefa">${tarefas[i].id_tarefa}</p>
                                <p class="nome-tarefa">${tarefas[i].tarefa_nome}</p>
                                <p hidden class="prioridade-tarefa">${tarefas[i].tarefa_prioridade}</p>
                                <input type = "checkbox" class = "tarefa-concluida" name = "tarefa-concluida" value = "concluido">
                                <label for = "tarefa-concluida">Tarefa concluída</label>
                            </div>
                        </li>`);
                        let container = document.getElementById(`container-${i}`)
                        if (tarefas[i].tarefa_prioridade == 1) {
                            container.style.background = '#ffd8ce';
                        }
                        else if (tarefas[i].tarefa_prioridade == 2) {
                            container.style.background = '#fff2ce';
                        }
                        else {
                            container.style.background = '#f4ffe5';
                        }
                    }
                }
            }
        })
}

// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}


function deleteTarefa(id_tarefa) {
    fetch(`http://localhost:6795/removeTarefas/${id_tarefa}`)
                        .then((response) => response.json())
                        .then((data) => {
                            displayMessage("Tarefa removida com sucesso!");
                            // Reexibe as tarefas
                            exibeTarefas();
                        })
                        .catch((error) => displayMessage("Erro ao remover tarefa"));
}


