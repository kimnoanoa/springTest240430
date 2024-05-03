/**
 * 
 */
console.log("222");

// cmtAddBtn버튼을 누르면 bno,writer,content 를 비동기로 DB에 넣기

document.getElementById('cmtAddBtn').addEventListener('click',()=>{

    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').value;

    if(cmtText == null | cmtText ==''){
        alert('댓글을 입력해주세요.');
        document.getElementById('cmtText').focus();
        return false;
    }

    let cmtData ={
        bno : bnoVal,
        writer : document.getElementById('cmtWriter').innerText,
        content : cmtText
    }

    console.log(cmtData);


    async function postCommentToServer(cmtData){
        try {
            const url = '/comment/post';
            const config = {
                method : 'post',
                headers : {
                    'content-type' : 'application/json; charset = utf-8'
                },
                body : JSON.stringify(cmtData)
            };
    
            const resp = await fetch(url,config);
            const result = await resp.text(); //isOk값
            return result;
    
        } catch (error) {
            console.log(error);
        }
    }


   
    postCommentToServer(cmtData).then(result=>{
        console.log(result);

        if(result ==1){
            alert('댓글이 정상적으로 등록되었습니다.');
            document.getElementById('cmtText').value ='';
            spreadCommentList(bnoVal);
        }
    })
});

    async function getCommentListFromServer(bno,page){
        try {
            //get은 요청만 하기 떄문에 config는 생략가능
            // /comment/list/306 도 가능 
            const resp = await fetch('/comment/'+bno+"/"+page);
            const result = await resp.json();
            return result;
        } catch (error) {
            console.log(error);
        }
        }



        function spreadCommentList(bno,page=1){
            getCommentListFromServer(bno,page).then(result =>{
                console.log(result);
                const ul = document.getElementById('cmtListArea');
                //ul.innerHTML ='';
                if(result.cmtList.length > 0){
                    if(page == 1){
                        ul.innerHTML='';
                    }
                   for(let cvo of result.cmtList){
                    let li = `<li class ="list-group-item" data-cno="${cvo.cno}">`;
                    li+= `<div class="input-group mb-3">no. ${cvo.cno} ►`;
                    li+= `<div class="fw-bold">${cvo.writer}</div><br>`;
                    li+=`${cvo.content}`;
                    li+= `</div>`;
                    li+= `<span class="badge rounded-pill text-bg-warning">${cvo.regDate}</span>`;
                    //수정 삭제버튼
                    li += `<button type="button"  class="btn btn-danger btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                    li += `<button type="button" data-cno="${cvo.cno}" class="btn btn-warning btn-sm del">삭제</button>`;
                    li+= `</li>`;
                    ul.innerHTML += li;
                   
                    }
                    //더보기버튼 코드
                    let moreBtn = document.getElementById('moreBtn');
                    console.log(moreBtn);
                    // moreBtn 표시되는 조건
                    // pgvo.pageNo = 1 / realEndPage = 3
                    // 

                    // realEndPage 보다 현재 내 페이지가 작으면 표시
                    if(result.pgvo.pageNo < result.realEndPage){
                       // style = "visibility: hidden" : 숨김 / "visibility: visible" : 표시
                       moreBtn.style.visibility = 'visible'; //버튼표시
                       moreBtn.dataset.page = page+1; //한페이지 늘림
                       
                    }else{
                        moreBtn.style.visibility ='hidden'; //숨김

                    }



                }else{
                   let li =`<li class="list-group-item">Comment List Empty</li>`;
                   ul.innerHTML =li;
                }
            })
        }

        document.addEventListener('click',(e)=>{
            if(e.target.id=='moreBtn'){
                let page = e.target.dataset.page;
                spreadCommentList(bnoVal, page);
            }
            //수정 시 모달창을 통해 댓글 입력받기 
          else if(e.target.classList.contains('mod')){
            //내가 수정버튼을 누른 댓글의 li
            let li = e.target.closest('li');
        
            //nextSlbling : 한 부모 안에서 다음 형제를 찾기
            let cmtText = li.querySelector('.fw-bold').nextSibling;
            console.log(cmtText);
            document.getElementById('cmtTextMod').value= cmtText.nodeValue;

            // 수정 => cno dataset 으로 달기, cno/content
            document.getElementById('cmtModBtn').setAttribute("data-cno",li.dataset.cno);
            }
            else if(e.target.id =='cmtModBtn'){
                let cmtModData ={
                    cno : e.target.dataset.cno,
                    content : document.getElementById('cmtTextMod').value
                };

                console.log(cmtModData);
                //비동기로 보내기

                modifyComment(cmtModData).then(result =>{
                   if(result =='1'){
                    alert('댓글 수정 완료');
                   spreadCommentList(bnoVal);
                   } 
                })
            }
            else if(e.target.classList.contains('del')){
                let cnoVal = e.target.dataset.cno;
                removeCommentToServer(cnoVal).then(result=>{
                    if(result =='1'){
                        alert('댓글 삭제가 완료되었습니다.');
                        spreadCommentList(bnoVal);
                    }
                });
            }
        });


        async function modifyComment(cmtModData){
            try {
                const url = '/comment/modify';
                const config = {
                    method : 'put',
                    headers : {
                        'content-type' : 'application/json; charset=utf-8'
                    },
                    body : JSON.stringify(cmtModData)
                };
        
                const resp = await fetch(url,config);
                const result = await resp.text(); //isOk값
                return result;
        
            } catch (error) {
                console.log(error);
            }
        }

        async function removeCommentToServer(cnoVal){
            try {
                const url = '/comment/'+cnoVal;
                const config = {
                    method : 'delete',
                };
        
                const resp = await fetch(url,config);
                const result = await resp.text(); //isOk값
                return result;
        
            } catch (error) {
                console.log(error);
            }
        }


        