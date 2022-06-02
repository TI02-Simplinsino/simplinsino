var novoid;

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais

function insertCadastro(campoNome, campoUsuario, campoEmail, campoSenha, cargo) {
  if (cargo == "Aluno") {
    fetch(`http://localhost:6795/getUltimoID`)
    .then((response) => response.json())
    .then((data) => {
      let novoId = parseInt(data) + 1;
      fetch(`http://localhost:6795/insertAluno/${novoId}/${campoNome}/${campoUsuario}/${campoEmail}/${campoSenha}`)
        .then((response) => response.json())
        .then((data) => {
          let novoCadastro = {
            nome_aluno: campoNome,
            id_aluno: novoId,
            email_aluno: campoEmail,
            senha_aluno: campoSenha,
            usuario_aluno: campoUsuario,
            cargo: cargo,
          };
          let cadastrados = []
          cadastrados[0] = novoCadastro
          sessionStorage.setItem('usuarioLogado',JSON.stringify(cadastrados))
          sessionStorage.setItem('cargo', cargo);
          modal();
        })
    })
    .catch((error) => alert("Erro ao inserir Pessoa"));
  }
  else if (cargo == "Professor") {
    fetch(`http://localhost:6795/getUltimoIDProfessor`)
    .then((response) => response.json())
    .then((data) => {
      let novoId = parseInt(data) + 1;
      fetch(`http://localhost:6795/insertProfessor/${novoId}/${campoNome}/${campoUsuario}/${campoEmail}/${campoSenha}`)
        .then((response) => response.json())
        .then((data) => {
          console.log(data)
          let novoCadastro = {
            nome_professor: campoNome,
            id_professor: novoId,
            email_professor: campoEmail,
            senha_professor: campoSenha,
            usuario_professor: campoUsuario,
            cargo: cargo,
          };
          let cadastrados = []
          cadastrados[0] = novoCadastro
          sessionStorage.setItem('usuarioLogado',JSON.stringify(cadastrados))
          sessionStorage.setItem('cargo', cargo);
          modal();
        })
    })
    .catch((error) => alert("Erro ao inserir Pessoa"));
  }
}



function modal() {
  modal = document.getElementById("modalCadastro");
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
    let campoConfirmSenha = $("#inputConfirmSenha").val();
    let cargo =  $("#inputCargoForm").val();
    let cadastro = {
      nome: campoNome,
      usuario: campoUsuario,
      email: campoEmail,
      senha: campoSenha,
      cargo: cargo,
      confirmSenha: campoConfirmSenha,
    };

    //Verifica se senha e confirmSenha são iguais
    if (cadastro.senha != cadastro.confirmSenha) {
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


    
    fetch(`https://api.hashify.net/hash/md5/hex?value=${campoSenha}`)
    .then((response) => response.json())
    .then((data) => {
        
      insertCadastro(campoNome, campoUsuario, campoEmail,data.Digest , cargo);
        
    })
    .catch((error) => alert("Erro "));
//nome_aluno,id_aluno,email_aluno,senha_aluno,usuario_aluno
    

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
