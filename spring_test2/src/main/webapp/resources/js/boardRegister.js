console.log("BoardJs in");

// 트리거 버튼 처리
document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();

});

// 실행파일에 대한 정규표현식 작성 /파일최대사이즈

const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024*1024*20;
function fileValidation(fileName,fileSize){
    if(regExp.test(fileName)){
        return 0;

    }else if(fileSize > maxSize){
        return 0;

    }else{
        return 1;
    }
}

document.addEventListener('change',(e)=>{
    if(e.target.id == 'file'){ // 파일에 변화가 생겼다면..
        // input type ="file" element 에 저장된 file 의 정보를 가져오는 property
        const fileObj = document.getElementById('file').files;
        console.log(fileObj);

        //한번 disabled되면 혼자 풀어질 수 없음. 버튼을 원래상태로 복구
        document.getElementById('regBtn').disbled = false;

        //등록된 file정보를 fileZone에 기록
        let div = document.getElementById('fileZone');
        div.innerHTML ='';
        //ul > li 로 파일 목록 추가

        // <ul class="list-group list-group-flush">
        // <li class="list-group-item">An item</li>


        // 여러개의 등록파일이 모두 검증을 통과해야하기 때문에
        // isOk 로 각각 파일이 통과할때마다 연산을 실행 -> 통과여부 확인
        let isOk =1;
        let ul = `<ul class="list-group list-group-flush">`;
        for (let file of fileObj){
            //
            let vaildResult = fileValidation(file.name , file.size);
            isOk *= vaildResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3>`;
            ul += `${vaildResult ? '<div class="fw-bold>업로드 가능</div>' : 'div class="fw-bold text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}</div><br>`;
            ul += `<span class="badge rounded-pill text-bg-${vaildResult ? 'success':'danger'}">${file.size}Bytes</span>`;
            ul += `</li>`;

        }
        ul += `</ul>`;
        div.innerHTML =ul;

        if(isOk == 0){
            document.getElementById('regBtn').disbled = true;
        }
    }
})