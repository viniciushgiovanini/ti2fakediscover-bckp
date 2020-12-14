const recuperaDados = () => {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', 'http://localhost:5000/dados', true);

    xhr.onload = () => {
        const dados = xhr.response;
        formatarDadosPraSaida(JSON.parse(dados));
    };

    xhr.send();
}

const formatarDadosPraSaida = (dados) => {
    let FakeNewsData = [];
    let perguntas = dados.Perguntas;
    let respostas = dados.Respostas;

    for (let i = 0; i < perguntas.length; i++) {
        perguntas[i].veracidade = perguntas[i].veracidade.split(" ")[0];
        FakeNewsData.push({
            afterAnswerContent: respostas[i].descricao,
            content: `<div id="noticeContent"> 
                <p id="noticeDescription"> ${perguntas[i].descricao}</p>
                <img class="img-notice" alt="Imagem sendo carregada..." src="./imgs/img${i}.jpg"/>
                <div id="inputsAndButtonDiv">
                    <div id="fakeOrTrueInputs">
                        <label for="falsa">Fake</label>
                        <input type="radio" name="veracidade" id="falsa" value="falsa">
                        <label for="verdadeira">Verdadeira</label>
                        <input type="radio" name="veracidade" id="verdadeira" value="verdadeira">
                    </div>
                    <button id="getFeedback">Obter feedback</button>
                </div>
            </div>`,
            isFake: (perguntas[i].veracidade === "verdadeira") ? true : false
        });
    }  

    console.log(FakeNewsData);
    
    disporNoticias(0, FakeNewsData, 0);
}

// dispor elementos na div principal da pagina jogo, isso na sequencia de noticias
const disporNoticias = (cont, FakeNewsData, points) => {
    let gameBox = document.querySelector('#parte-noticia');
    let nPoints = document.querySelector('#numero-pontuacao');
    nPoints.textContent = points;
    gameBox.innerHTML = FakeNewsData[cont].content;
    let getFeed = document.querySelector('#getFeedback');
    getFeed.addEventListener('click', () => {
        let correctAnswer = `<div id='feedbackDiv'>
                                <h3 id='correctH3'> Correto!! </h3>
                                <p id='feedDescription'> ${FakeNewsData[cont].afterAnswerContent} </p>
                            </div>`;
        let wrongAnswer = `<div id='feedbackDiv'>
                                <h3 id='wrongH3'> Errado!! </h3>
                                <p id='feedDescription'> ${FakeNewsData[cont].afterAnswerContent} </p>
                            </div>`;
        let nextNoticeButton = '<button id="nextNotice"> Próxima notícia </button>';
        let radioButton = document.querySelector('input[name="veracidade"]:checked');
        if (radioButton) {
            let radioValue = radioButton.value;
            if (radioValue == null)
            alert("Você deve selecionar uma das opções!");
            radioValue = (radioValue == 'verdadeira') ? true : false;
            if (FakeNewsData[cont].isFake == radioValue) {
                gameBox.innerHTML = correctAnswer + nextNoticeButton;
                points += 500;
            } else {
                gameBox.innerHTML = wrongAnswer + nextNoticeButton;
            }
            document.querySelector('#nextNotice').onclick = () => {
                if (cont < FakeNewsData.length-1) {
                    disporNoticias(++cont, FakeNewsData, points);
                } else {
                    document.querySelector('#placar').style.display = 'none';
                    gameBox.innerHTML = `<div id="theEnd">
                                        <h2 id="endP"> Fim do jogo! </h2>
                                        <h2 id="finalPoints"> Sua pontuação: ${points} </h2>
                                        <img id="good-homer" src="./imgs/muito_bem_homer.jpg" />
                                    </div>`;
                }
            }
        } else {
            alert("Você deve selecionar uma das opções!");
        }
        
    });
}

window.onload = () => {
    recuperaDados();
}
