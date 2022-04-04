function preencheForm() {
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    let form = document.getElementById('form-componentes')
    if (usuario) {
            let htmlStr = `<input type="text" class="form-control" id="inputId" hidden disabled>
                            
                        <label for="inputNome" class="form-label">Nome Completo:</label>
                        <input type="text" class="form-control" id="inputNome" value="${usuario.nome}" required>

                        <label for="inputUsuario" class="form-label">Nome de Usuário</label>
                        <input type="text" class="form-control" id="inputUsuario" value="${usuario.usuario}" required>

                        <label for="inputEmail" class="form-label">Endereço de Email</label>
                        <input type="email" class="form-control" id="inputEmail" value="${usuario.email}" required>

                        <label for="inputPassword" class="form-label">Digite uma senha</label>
                        <input type="password" class="form-control" id="inputSenha" required>

                        <label for="inputPassword" class="form-label">Confirme sua senha</label>
                        <input type="password" class="form-control" id="inputConfirmSenha" required>`

            form.innerHTML = htmlStr
    }
}



function apagarPerfil() {
    // FALTA: alerta: certeza que quer apagar o perfil? se sim proceder e apagar
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    let id = usuario.id
    let dbCadastro = JSON.parse(localStorage.getItem('db_cadastro'));
    // apagar perfil do local storage
    dbCadastro = dbCadastro.filter(function (element) { return element.id != id });
    // Atualiza os dados no Local Storage
    localStorage.setItem('db_cadastro', JSON.stringify(dbCadastro));

    //Deslogar usuario
    LogoutUser()
}

function salvarAlteracoes() {
    modal = document.getElementById('modalAlterarPerfil')
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
    //Verifica se senha e confirmSenha são iguais
    if($("#inputSenha").val() != $("#inputConfirmSenha").val()){
      modal.innerHTML = `<div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body" id="modalBody">
                                                <h6>A sua senha não pode ser confirmada</h6>
                                            </div>
                                            <div class="modal-footer">
                                            </div>
                                        </div>
                                    </div>`;
      return
    }
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    let dbCadastro = JSON.parse(localStorage.getItem('db_cadastro'));
    let index = dbCadastro.map((obj) => obj.id).indexOf(usuario.id);
    usuario.nome = dbCadastro[index].nome =  $("#inputNome").val();
    usuario.usuario = dbCadastro[index].usuario = $("#inputUsuario").val();
    usuario.email = dbCadastro[index].email = $("#inputEmail").val();
    usuario.senha = dbCadastro[index].senha = $("#inputSenha").val();
    usuario.papel = dbCadastro[index].papel = $("#inputPapel").val();
    usuario.confirmSenha = dbCadastro[index].confirmSenha = $("#inputConfirmSenha").val();

    localStorage.setItem("db_cadastro", JSON.stringify(dbCadastro));
    sessionStorage.setItem ('usuarioLogado', JSON.stringify (usuario));
    modal.innerHTML = `<div class="modal-dialog modal-dialog-centered">
                          <div class="modal-content">
                              <div class="modal-header">
                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body" id="modalBody">
                                  <h6>dados alterados com sucesso</h6>
                              </div>
                              <div class="modal-footer">
                              </div>
                          </div>
                      </div>`;
    $('#modalAlterarPerfil').on('hidden.bs.modal', function () {
        location.reload();
    });
  }

