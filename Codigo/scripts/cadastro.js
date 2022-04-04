var dbCadastro = JSON.parse(localStorage.getItem("db_cadastro"));

function initCadastro() {
  if (!dbCadastro) {
    localStorage.setItem("db_cadastro", JSON.stringify(db.cadastros));
    document.location.reload();
  }
}

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais

function insertCadastro(cadastro) {
  console.log(db.cadastros);

  // Calcula novo Id a partir do último código existente no array (PODE GERAR ERRO SE A BASE ESTIVER VAZIA)
  let novoId = dbCadastro[dbCadastro.length - 1].id + 1;
  console.log(novoId);
  let novoCadastro = {
    id: novoId,
    nome: cadastro.nome,
    email: cadastro.email,
    usuario: cadastro.usuario,
    senha: cadastro.senha,
    papel: cadastro.papel,
    confirmSenha: cadastro.confirmSenha,
  };
  console.log(cadastro.nome);

  // Insere o novo objeto no array
  dbCadastro.push(novoCadastro);

  // Atualiza os dados no Local Storage
  localStorage.setItem("db_cadastro", JSON.stringify(dbCadastro));
}

function init() {
  modal = document.getElementById("modalCadastro");

  // Adiciona funções para tratar os eventos
  $("#btnInsert").click(function () {
    
    // Obtem os valores dos campos do formulário
    let campoNome = $("#inputNome").val();
    let campoUsuario = $("#inputUsuario").val();
    let campoEmail = $("#inputEmail").val();
    let campoSenha = $("#inputSenha").val();
    let campoPapel = $("#inputPapel").val();
    let campoConfirmSenha = $("#inputConfirmSenha").val();
    let cadastro = {
      nome: campoNome,
      usuario: campoUsuario,
      email: campoEmail,
      senha: campoSenha,
      papel: campoPapel,
      confirmSenha: campoConfirmSenha,
    };

    //Verifica se senha e confirmSenha são iguais
    if(cadastro.senha != cadastro.confirmSenha){
      modal.innerHTML = `<div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body" id="modalBody">
                                                <h6>A sua senha não pôde ser confirmada</h6>
                                            </div>
                                            <div class="modal-footer">
                                            </div>
                                        </div>
                                    </div>`;
      return
    }
    // Verfica se o formulário está preenchido corretamente
    if (!$("#form-cadastro")[0].checkValidity()) {
      modal.innerHTML = `<div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body" id="modalBody">
                                                <h6>preencha os dados do formulário corretamente</h6>
                                            </div>
                                            <div class="modal-footer">
                                            </div>
                                        </div>
                                    </div>`;
      return;
    }


    insertCadastro(cadastro);

    loginUser(campoUsuario, campoSenha);
    modal.innerHTML = `<div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="window.location.href = 'index.html';"></button>
                                </div>
                                <div class="modal-body" id="modalBody">
                                    <h6>usuário cadastrado e logado com sucesso</h6>
                                </div>
                                <div class="modal-footer">
                                </div>
                            </div>
                        </div>`;
    $('#modalCadastro').on('hidden.bs.modal', function () {
            location.reload();
    });
    // Limpa o formulario
    $("#form-cadastro")[0].reset();
  });
}
