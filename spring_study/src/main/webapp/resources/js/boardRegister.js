/**
 * 
 */
console.log("sssssssss");

document.getElementById('trigger').addEventListener('click',()=>{
	document.getElementById('file').click();


});

//정규표현식을 사용하여 파일의 형식을 제한
// 실행파일 막기(exe, bat, sh,mis,dll,jar)
// 파일 사이즈 체크 (20M 사이즈보다 크면 막기)
// 이미지 파일만 올리기( jpg,jpeg,gif,png,bmp)

const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$");
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmo)$");
const maxSize = 1024*1024*20;

//Validation : 규칙설정
//return 0 /1 
function fileValidation(name,fileSize){
    //apple.jpeg
    let fileName = name.toLowerCase(); //파일이름을 전부 소문자로 변경
    if(regExp.test(fileName)){ //파일확장자에 실행파일 확장자가 있다면

        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else if(!regExpImg.test(fileName)){
        return 0;
    }else{
        return 1;
    }

}

// 첨부파일에 따라 등록이 가능한지 체크함수

document.addEventListener('change',(e)=>{
    console.log(e.target);
    if(e.target.id ==='file'){
        //여러개의 파일이 배열로 들어옴
        const fileObject = document.getElementById('file').files;
        console.log("fileObject");

        // 한번 트루가 된 disabled는 다시 false로 돌아갈 수 없음 . 버튼활성화
        document.getElementById('regBtn').disabled = false;

        let div = document.getElementById('fileZone');
        div.innerHTML =''; //기존 등록한 파일이 있다면 지우기
        let ul = `<ul class="list-group">`;
        //각각의 파일은 fileValidation에 의해 리턴여부를체크
        // 모든 파일의 return 값이 1이어야 가능
        // * 로 isOk를 처리하여 모든파일이1인지 체크
        let isOk =1;
        for(let file of fileObject){
            let validResult = fileValidation(file.name,file.size); //한 파일에 대한 결과
            isOk *= validResult;
            ul+= `<li class="list-group-item">`;
            ul+= `<div>${validResult ? '업로드 가능':'업로드 불가능'}</div> ${file.name}`;
            ul+= `<span class="badge text-bg-${validResult ? 'success':'danger'}">${file.size}</span>`;
            ul+= `</li>`;
        }
        ul+= `</ul>`;
        div.innerHTML = ul;
        //업로드가 불가능한 파일이 1개라도 있으면 버튼을 비활성화
        if(isOk ==0){
            document.getElementById('regBtn').disabled =true;
        }
    }
})
/*
 <ul class="list-group">
 <li class="list-group-item"><div>업로드 가능</div> 파일이름
 <span class="badge text-bg-success">파일사이즈</span>
 </li>
  <li class="list-group-item"><div>업로드 불가능</div> 파일이름
  <span class="badge text-bg-danger">파일사이즈</span>
  </li>
 </ul>
*/