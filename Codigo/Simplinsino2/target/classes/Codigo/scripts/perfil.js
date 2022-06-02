function preencheForm() {
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    let form = document.getElementById('form-componentes')
    if (sessionStorage.cargo == 'Aluno') {
            let htmlStr = `<input type="text" class="form-control" id="inputId" hidden disabled>
                            
                        <label for="inputNome" class="form-label">Nome Completo:</label>
                        <input type="text" class="form-control" id="inputNome" value="${usuario[0].nome_aluno}" required>

                        <label for="inputUsuario" class="form-label">Nome de Usuário</label>
                        <input type="text" class="form-control" id="inputUsuario" value="${usuario[0].usuario_aluno}" required>

                        <label for="inputEmail" class="form-label">Endereço de Email</label>
                        <input type="email" class="form-control" id="inputEmail" value="${usuario[0].email_aluno}" required>

                        <label for="inputPassword" class="form-label">Digite uma senha</label>
                        <input type="password" class="form-control" id="inputSenha" required>

                        <label for="inputPassword" class="form-label">Confirme sua senha</label>
                        <input type="password" class="form-control" id="inputConfirmSenha" required>`

            form.innerHTML = htmlStr
    }
    else if  (sessionStorage.cargo == 'Professor') {
        let htmlStr = `<input type="text" class="form-control" id="inputId" hidden disabled>
                        
                    <label for="inputNome" class="form-label">Nome Completo:</label>
                    <input type="text" class="form-control" id="inputNome" value="${usuario[0].nome_professor}" required>

                    <label for="inputUsuario" class="form-label">Nome de Usuário</label>
                    <input type="text" class="form-control" id="inputUsuario" value="${usuario[0].usuario_professor}" required>

                    <label for="inputEmail" class="form-label">Endereço de Email</label>
                    <input type="email" class="form-control" id="inputEmail" value="${usuario[0].email_professor}" required>

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
    let id = usuario[0].id_aluno
    fetch(`http://localhost:6795/removeAluno/${id}`)
    .then((response) => response.json())
    .then((data) => {
        alert("Pessoa removida com sucesso!");
    })
    


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
    /*
    nome_aluno: campoNome,
    id_aluno: novoId,
    email_aluno: campoEmail,
    senha_aluno: campoSenha,
    usuario_aluno: campoUsuario,*/

    if (sessionStorage.cargo == 'Aluno') {
        let nome_aluno = $("#inputNome").val();
        let id_aluno = usuario[0].id_aluno;
        let email_aluno = $("#inputEmail").val();
        let senha_aluno = $("#inputSenha").val();
        let usuario_aluno = $("#inputUsuario").val();
    
    
    
        fetch(`https://api.hashify.net/hash/md5/hex?value=${senha_aluno}`)
        .then((response) => response.json())
        .then((data) => {
            
            fetch(`http://localhost:6795/updateAluno/${id_aluno}/${usuario_aluno}/${nome_aluno}/${email_aluno}/${data.Digest}`)
            .then((response) => response.json())
            .then((data) => {
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
        
                
            })
            
        })
        .catch((error) => alert("Erro "));

        
        let novoCadastro = {
            nome_aluno: nome_aluno,
            id_aluno: id_aluno,
            email_aluno: email_aluno,
            senha_aluno: senha_aluno,
            usuario_aluno: usuario_aluno,
          };
          let cadastrados = []
          cadastrados[0] = novoCadastro
          sessionStorage.setItem('usuarioLogado',JSON.stringify(cadastrados))
    
    
        localStorage.setItem("db_cadastro", JSON.stringify(dbCadastro));
    
        
        $('#modalAlterarPerfil').on('hidden.bs.modal', function () {
            location.reload();
        });
    }

    else if(sessionStorage.cargo == 'Professor'){

        let nome_professor = $("#inputNome").val();
        let id_professor = usuario[0].id_professor;
        let email_professor = $("#inputEmail").val();
        let senha_professor = $("#inputSenha").val();
        let usuario_professor = $("#inputUsuario").val();
    
    
    
        fetch(`https://api.hashify.net/hash/md5/hex?value=${senha_professor}`)
        .then((response) => response.json())
        .then((data) => {
            x = data
            alert(x.Digest)
            fetch(`http://localhost:6795/updateProfessor/${id_professor}/${usuario_professor}/${nome_professor}/${email_professor}/${x.Digest}`)
            .then((response) => response.json())
            .then((data) => {
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
        
                
            })
            
        })
        .catch((error) => alert("Erro "));

        
        let novoCadastro = {
            nome_professor: nome_professor,
            id_professor: id_professor,
            email_professor: email_professor,
            senha_professor: senha_professor,
            usuario_professor: usuario_professor,
          };
          let cadastrados = []
          cadastrados[0] = novoCadastro
          sessionStorage.setItem('usuarioLogado',JSON.stringify(cadastrados))
    
    
        localStorage.setItem("db_cadastro", JSON.stringify(dbCadastro));
    
        
        $('#modalAlterarPerfil').on('hidden.bs.modal', function () {
            location.reload();
        });
    }
  }

