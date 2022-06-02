// carrega o menu de disciplinas
function carregaDisciplinas () {

    fetch("http://localhost:6805/getDisciplinas")
        .then((response) => response.json())
        .then((disciplinas) =>{
            if(disciplinas.length > 0) {
                let menuDisciplinas = document.getElementById('menu_disciplinas')
                let htmlStr = ``
                for (i = 0; i < disciplinas.length; i++) {
                    htmlStr += `<li><a class="dropdown-item" href="disciplina.html?id=${disciplinas[i].id_disciplina}">${disciplinas[i].disciplina_nome}</a></li>`
                }
                
                menuDisciplinas.innerHTML = htmlStr
            }
        })
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

// carrega a lista de cursos cadastrados por 
async function carregaCursos(){
	
	let idDisciplina = getUrlVars()["id"]
	const respDisciplina = await fetch(`http://localhost:6805/search/${idDisciplina}`)
	const disciplina = await respDisciplina.json()
	const respCursos = await fetch(`http://localhost:6800/getCursos`)
	const cursos = await respCursos.json()
	
	let containerCurso = document.getElementById('lista_cursos')
	let htmlStr = ''
	
	for (i = 0; i < cursos.length; i++) {
		let idCurso = cursos[i].id_curso
		const respVideo = await fetch(`http://localhost:6815/searchByCurso/${idCurso}`)
		const video = await respVideo.json()
		
		if (video.length > 0) {
											
			if (cursos[i].id_disciplinafk == idDisciplina) {
	
	            htmlStr +=
	            
	            `<li class="container-curso">
	                <a href="curso.html?idCurso=${cursos[i].id_curso}&id=0" class="col-lg-3">
	                    <img src="${cursos[i].curso_imagem}" alt="">
	                </a>
	                <div class="container-texto">
	                    <div class="container-btn-titulo"> 
	                        <a href="curso.html?idCurso=${cursos[i].id_curso}&id=${video[0].id_video}"><h5>${cursos[i].curso_nome}</h5></a>
	                        <button type="button" onclick="salvarCurso(${cursos[i].id_curso})" data-bs-toggle="modal" data-bs-target="#modalAlerta"><ion-icon name="heart" class="btn-coracao"></ion-icon></button>
	                    </div>
	                    <a href="curso.html?idCurso=${cursos[i].id_curso}&id=${video[0].id_video}">
	                        <p class="disciplina"><span>Disciplina:</span> ${disciplina[0].disciplina_nome}</p>
	                        <p class="disciplina"><span>Descrição:</span> ${cursos[i].curso_descricao}</p>
	                    </a>
	                </div>
	            </li>`
	        
			}
		}
	}
	containerCurso.innerHTML = htmlStr
}

// carrega o menu na pagina inicial com as disciplinas e os respectvos icones
function carregaDisciplinasIndex () {

    fetch("http://localhost:6805/getDisciplinas")
        .then((response) => response.json())
        .then((disciplinas) =>{
            if(disciplinas.length > 0) {
                let menuIndex = document.getElementById('menuIndex')
    			let htmlStr = ''
                for (i = 0; i < disciplinas.length; i++) {

                    htmlStr +=
			            `<li class="col-6 col-sm-6 col-md-3 col-lg-3 container-mat">
			                <img src="${disciplinas[i].disciplina_img}" alt="">
			                <a href="disciplina.html?id=${disciplinas[i].id_disciplina}" class="btn">${disciplinas[i].disciplina_nome}</a>
			            </li>`
                }
                menuIndex.innerHTML = htmlStr
            }
        })
}
