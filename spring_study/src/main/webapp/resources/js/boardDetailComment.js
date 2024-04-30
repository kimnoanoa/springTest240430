console.log(bnoVal);

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
    const cmtWriter = document.getElementById('cmtWriter').innerText;
    const cmtText = document.getElementById('cmtText').value;
    if(cmtText == null || cmtText == ''){
        alert("댓글을 입력해주세요. ")
        document.getElementById('cmtText').focus();
        return false;
    }else{
        let cmtData = {
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        }
        console.log(cmtData);
        postCommentToServer(cmtData).then(result => {
            console.log(result);
            if(result == '1'){
                alert("댓글 입력 완료");
                document.getElementById('cmtText').value = "";
                //화면에 뿌리기
                spreadCommentList(bnoVal);
            }
        })
    }
});


//비동기 통신 result
//post : 데이터 객체를 컨트롤러로 보낼때 (삽입)
//get : 조회(생략가능)
//put(patch) : 수정
//delete : 삭제
async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method : 'post',
            headers : {
                'Content-Type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
function spreadCommentList(bno){
    getCommentListFromServer(bno).then(result =>{
        console.log(result);
        const div = document.getElementById('accordionExample');
        if(result.length > 0){
            div.innerHTML = "";
            for(let i=0; i<result.length; i++){
                let add = `<div class="accordion-item">`;
                add += `<h2 class="accordion-header">`;
                add += `<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
                add += `no.${result[i].cno} / ${result[i].writer} / ${result[i].reg_date} `;
                add += `</button></h2>`;
                add += `<div id="collapse${i}" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">`;
                add += `<div class="accordion-body">`;
                add += `<input type="text" class="form-control cmtText" value="${result[i].content}">`;
                if(logVal === result[i].writer){
                    add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-danger btn-sm cmtModBtn">수정</button>`;
                    add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-warning btn-sm cmtDelBtn">삭제</button>`;
                };
                add += `</div></div></div>`;
                div.innerHTML += add;
            }
        }else{
            div.innerHTML = `<div class="accordion-body"> Comment List Empty </div>`;
        }
    })
}

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('cmtModBtn')){
        let cnoVal = e.target.dataset.cno;
        let div = e.target.closest('div');
        let cmtText = div.querySelector('.cmtText').value;
        cmtData = {
            cno : cnoVal,
            content : cmtText
        };
        updateCommentToServer(cmtData).then(result => {
            if(result == "1"){
                alert("댓글 수정 완료");
                spreadCommentList(bnoVal);
            }
        })
    }
    if(e.target.classList.contains('cmtDelBtn')){
        let cnoVal = e.target.dataset.cno;
        removeCommentFromServer(cnoVal).then(result =>{
            if(result == "1"){
                alert("댓글 삭제 완료");
                spreadCommentList(bnoVal);
            }
        })
    }
})

async function getCommentListFromServer(bno){
    try {
        const resp = await fetch("/comment/"+bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function updateCommentToServer(cmtData){
    try {
        const url = "/comment/modify";
        const config = {
            method : "put",
            headers : {
                'Content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function removeCommentFromServer(cno){
    try {
        const url = "/comment/"+cno;
        const config = {
            method : "delete"
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
