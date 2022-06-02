function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
        function (m, key, value) {
            vars[key] = value;
        });

    return vars;
}

function carregaNomeCurso () {
	
	let idCurso = getUrlVars()["idCurso"]
    fetch(`http://localhost:6800/search/${idCurso}`)
        .then((response) => response.json())
        .then((curso) =>{
            if(curso.length > 0) {
                let tituloCurso = document.getElementById('nomeCurso')
    			let htmlStr = ''
    			
    			htmlStr += `<h1 id="nomeCurso">${curso[0].curso_nome}</h1>`
                tituloCurso.innerHTML = htmlStr
            }
        })
}


function carregaNomeDisciplina() {
	
	let idCurso = getUrlVars()["idCurso"]
	
	fetch(`http://localhost:6800/search/${idCurso}`)
        .then((response) => response.json())
        .then((curso) =>{
		
			let idDisciplina = curso[0].id_disciplinafk

			fetch(`http://localhost:6805/search/${idDisciplina}`)
		        .then((response) => response.json())
		        .then((disciplina) =>{
		            
		            if(disciplina.length > 0) {
						
						let btnVoltar = document.getElementById('btnVoltar')
		    		
						let htmlStr = `<a class="btn voltar" href="disciplina.html?id=${disciplina[0].id_disciplina}">voltar para ${disciplina[0].disciplina_nome}</a>`
		            
		            	btnVoltar.innerHTML = htmlStr
		            }
		            
		            
		        })
		})
}

function carregaConteudoCurso() {

    let idCurso = getUrlVars()["idCurso"]
    let idVideo = getUrlVars()["id"]

	fetch(`http://localhost:6800/search/${idCurso}`)
        .then((response) => response.json())
        .then((curso) =>{
		
			let idDisciplina = curso[0].id_disciplinafk
		
			fetch(`http://localhost:6805/search/${idDisciplina}`)
		        .then((response) => response.json())
		        .then((disciplina) =>{
			
		            fetch(`http://localhost:6815/search/${idVideo}`)
		        		.then((response) => response.json())
		        		.then((video) =>{
		            		if (video.length > 0) {
			
								let containerConteudo = document.getElementById('conteudoCurso')
		            		
			            		let htmlStr = ''
							    htmlStr +=
							        `<div class="container-curso-video">
							                <iframe src="${video[0].link_video}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
							            </div>
							            <div class="container-curso-texto">
							                <h5>${video[0].nome_video}</h5>
							                <p><span>&#8226; Disciplina: </span>${disciplina[0].disciplina_nome}</p>
							                <p><span>&#8226; Descrição: </span>${video[0].descricao_video}</p>
							          </div>`
							
							    containerConteudo.innerHTML = htmlStr
							}
		            		
		        		})
	            
	        	})
		})
}

function carregaMenuConteudo() {
	
	let idCurso = getUrlVars()["idCurso"]
	
    fetch("http://localhost:6815/getVideos")
        .then((response) => response.json())
        .then((videos) =>{
            if (videos.length > 0) {
				let menuConteudo = document.getElementById('menuConteudo')
				let htmlStr = ''
	            
	            for (i = 0; i < videos.length; i++) {
					if (videos[i].id_cursofk == idCurso) {
						console.log("video: ");
						console.log(videos[i].nome_video);
						htmlStr += `<li><a href="curso.html?idCurso=${idCurso}&id=${videos[i].id_video}" class="btn">${videos[i].nome_video}</a></li>`
					}
				}
				menuConteudo.innerHTML = htmlStr
			}
        })
}


function carregaTitle () {
	
	let idCurso = getUrlVars()["idCurso"]
    fetch(`http://localhost:6800/search/${idCurso}`)
        .then((response) => response.json())
        .then((curso) =>{
            if(curso.length > 0) {
				document.title = curso[0].curso_nome
            }
        })
}
