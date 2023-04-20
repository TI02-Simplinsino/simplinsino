var dbCursosSalvos = {
    salvos: [],
};

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
        function (m, key, value) {
            vars[key] = value;
        });

    return vars;
}


function salvarCurso(idCurso) {
    let teste = 0;
    let tmp = {};
    let dataSalvos;
    let htmlStr = ''
    let modal = document.getElementById('modalAlerta')
    // pagina so e acessada se o usuario estiver logado
    // puxar dados do usuario para  salvar o curso associado ao id do usuario
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    if  (!usuario) {
        htmlStr = 
                `<div class="modal-dialog modal-dialog-centered modal-md">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h6>faça login para conseguir salvar esse curso!</h6>
                        </div>
                        <div class="modal-footer">
                        </div>
                    </div>
                </div>`
    }
    else {
        dataSalvos = JSON.parse(localStorage.getItem("cursosSalvos"))
        if (!dataSalvos) {
            dataSalvos = dbCursosSalvos.salvos
        }
        // se o curso ja tiver sido salvo pelo usuario teste = 1
        for (i = 0; i < dataSalvos.length; i++) {
            if ((dataSalvos[i].idCurso == idCurso) && (usuario.id === dataSalvos[i].idUsuario)) {
                teste = 1;
            }
        }
        // se o curso nao tiver sido salvado pelo usuario
        if (teste == 0) {
            htmlStr = 
                `<div class="modal-dialog modal-dialog-centered modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h6>curso salvo com sucesso!</h6>
                        </div>
                        <div class="modal-footer">
                        </div>
                    </div>
                </div>`
            let curso = db.cursos[idCurso]
            tmp = {
                "idDisciplina": curso.idDisciplina,
                "idCurso": idCurso,
                "nomeCurso": curso.nomeCurso,
                "descricao": curso.descricao,
                "img": curso.img,
                "idUsuario": usuario.id,
            }
            dataSalvos.push(tmp);
        }
        // se ja tiver sido salvado, nao salvar mais uma vez
        else {
            htmlStr = 
                `<div class="modal-dialog modal-dialog-centered modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h6>esse curso já estava salvo!</h6>
                        </div>
                        <div class="modal-footer">
                        </div>
                    </div>
                </div>`
        }
        localStorage.setItem("cursosSalvos", JSON.stringify(dataSalvos));
    }
    
    modal.innerHTML = htmlStr;
}

function btnclick() {
    let btnSalvar = document.getElementById('btn-Salvar')
    btnSalvar.onclick = salvarCurso(getUrlVars()["idCurso"])
}


// carrega a lista de cursos salvos
function carregaCursosSalvos() {
    let containerCurso = document.getElementById('lista-cursos-salvos')
    let htmlStr = ''
    // puxar dados do usuario para carregar os cursos salvos por AQUELE usuario
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    dataSalvos = JSON.parse(localStorage.getItem("cursosSalvos"))
    if (!dataSalvos) {
        dataSalvos = dbCursosSalvos.salvos
    }
    for (i = 0; i < dataSalvos.length; i++) {
        if (usuario.id == dataSalvos[i].idUsuario)
        {
            let curso = dataSalvos[i]
            htmlStr +=
                `<li class="container-curso">
                    <a href="curso.html?idCurso=${curso.idCurso}&id=0" class="col-lg-3">
                        <img src="${curso.img}" alt="">
                    </a>
                    <div class="container-texto">
                        <div class="container-btn-titulo"> 
                            <a href="curso.html?idCurso=${curso.idCurso}&id=0"><h5>${curso.nomeCurso}</h5></a>
                            <button type="button" onclick="removeCurso(${i})"><ion-icon name="heart" class="btn-coracao"></ion-icon></button>
                        </div>
                        <a href="curso.html?idCurso=${curso.idCurso}&id=0">
                            <p class="disciplina"><span>Disciplina:</span> ${db.disciplinas[curso.idDisciplina].titulo}</p>
                            <p class="disciplina"><span>Descrição:</span> ${curso.descricao}</p>
                        </a>
                    </div>
                </li>`
        }
    }
    containerCurso.innerHTML = htmlStr
}



// remover curso selecionado pelo usuario
function removeCurso(index) {
    dataSalvos = JSON.parse(localStorage.getItem("cursosSalvos"))

    dataSalvos.splice(index, 1);
    localStorage.setItem("cursosSalvos", JSON.stringify(dataSalvos))

    carregaCursosSalvos()
}

//carrega usuario logado no menu a esquerda da página de cursos salvos
function carregaUsuario() {
    let usuario = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    let user = document.getElementById('UserCursos')
    user.innerHTML = `<h6 id="UserCursos">@${usuario[0].usuario_aluno}</h6>`
}