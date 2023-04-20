// carrega o menu de disciplinas
function carregaDisciplinas() {
    let menuDisciplinas = document.getElementById('menu_disciplinas')
    let htmlStr = ''
    for (i = 0; i < db.disciplinas.length; i++) {
        let disciplina = db.disciplinas[i]
        htmlStr += `<li><a class="dropdown-item" href="disciplina.html?id=${disciplina.id}">${disciplina.titulo}</a></li>`
    }
    menuDisciplinas.innerHTML = htmlStr
}


function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
        function (m, key, value) {
            vars[key] = value;
        });

    return vars;
}


// carrega a listra de sites indicados por disciplinas
function carregaListaSites() {
    let listaSites = document.getElementById('lista-sites')
    let id = getUrlVars()["id"]
    let sites = db.disciplinas[id].site
    let htmlStr = ''

    for (i = 0; i < sites.length; i++) {
        let site = sites[i]
        htmlStr += `<li><a href="${site.url}" target="_blank" class="btn">${site.nome}</a></li>`
    }
    listaSites.innerHTML = htmlStr
}


// carrega o titulo da pagina (nome da disciplina)
function carregaTitulo() {
    let titulo = document.getElementById('titulo')
    let id = getUrlVars()["id"]
    let titulo_pag = db.disciplinas[id].titulo
    let htmlStr = ''

    htmlStr += `${titulo_pag}`

    titulo.innerHTML = htmlStr
    document.title = titulo_pag
}


// carrega a lista de cursos cadastrados por disciplina
function carregaCursos() {
    let containerCurso = document.getElementById('lista_cursos')
    let id = getUrlVars()["id"]
    let htmlStr = ''
    for (i = 0; i < db.cursos.length; i++) {
        if (db.cursos[i].idDisciplina == id) {
            let curso = db.cursos[i]
            htmlStr +=
            `<li class="container-curso">
                <a href="curso.html?idCurso=${curso.idCurso}&id=0" class="col-lg-3">
                    <img src="${curso.img}" alt="">
                </a>
                <div class="container-texto">
                    <div class="container-btn-titulo"> 
                        <a href="curso.html?idCurso=${curso.idCurso}&id=0"><h5>${curso.nomeCurso}</h5></a>
                        <button type="button" onclick="salvarCurso(${curso.idCurso})" data-bs-toggle="modal" data-bs-target="#modalAlerta"><ion-icon name="heart" class="btn-coracao"></ion-icon></button>
                    </div>
                    <a href="curso.html?idCurso=${curso.idCurso}&id=0">
                        <p class="disciplina"><span>Disciplina:</span> ${db.disciplinas[curso.idDisciplina].titulo}</p>
                        <p class="disciplina"><span>Descrição:</span> ${curso.descricao}</p>
                    </a>
                </div>
            </li>`
        }
    }
    //<button class="btn salvar" id="btn-Salvar" data-bs-toggle="modal" data-bs-target="#modalAlerta">salvar curso</button>
    containerCurso.innerHTML = htmlStr
}


// carrega o menu na pagina inicial com as disciplinas e os respectvos icones
function carregaDisciplinasIndex() {
    let menuIndex = document.getElementById('menuIndex')
    let htmlStr = ''
    for (i = 0; i < db.disciplinas.length; i++) {
        let disciplina = db.disciplinas[i]
        htmlStr +=
            `<li class="col-6 col-sm-6 col-md-3 col-lg-3 container-mat">
                <img src="${disciplina.icone}" alt="">
                <a href="disciplina.html?id=${disciplina.id}" class="btn">${disciplina.titulo}</a>
            </li>`
    }
    menuIndex.innerHTML = htmlStr
}