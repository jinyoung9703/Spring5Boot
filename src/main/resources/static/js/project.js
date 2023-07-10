// agree
let agree1 = document.querySelector("#agree1");
let agree2 = document.querySelector("#agree2");
let okagree = document.querySelector("#okagree");
let noagree = document.querySelector("#noagree");

okagree?.addEventListener('click',() => {
    if(!agree1.checked) alert('이용약관에 동의하세요!');
    else if(!agree2.checked) alert('개인정보 이용 동의에 체크하세요!');
    else location.href = "/join/checkme";
});
noagree?.addEventListener('click',() => {
    location.href = "/";
});

//checkme
let frm = document.forms.checkfrm2;
let checkbtn2 = document.querySelector("#checkbtn2");
let cancelbtn2 = document.querySelector("#cancelbtn2");
checkbtn2?.addEventListener('click',()=>{
    if (frm.name.value === '') alert('이름를 입력하세요!!');
    else if(frm.jumin1.value === '') alert('주민번호를 입력하세요!!');
    else if(frm.jumin2.value === '') alert('나머지 주민번호를 입력하세요!!');
    else if(!frm.infoagree.checked) alert('주민번호 처리에 동의하세요!!');
    else {
        frm.method = 'post';
        frm.submit();
    }

})
cancelbtn2?.addEventListener('click',()=>{
    location.href = "/";
})
// joinme
let fzipbtn= document.querySelector("#findzipbtn");
let zipbtn= document.querySelector("#zipbtn");
let dong = document.querySelector("#dong");
let zipmodal = document.querySelector("#zipmodal");
let addrlist = document.querySelector("#addrlist");
let sendzip = document.querySelector("#sendzip");
let modal = null;   // 우편번호 모달(검색)

let email3 = document.querySelector("#email3");

zipbtn?.addEventListener('click', ()=> {
    while(addrlist.lastChild){
        addrlist.removeChild(addrlist.lastChild);
    }// 이전 검색 결과 지움
    dong.value = '';    // 이전 검색 키워드 지움

    try {
        // 새로운 모달 창 생성
        modal = new bootstrap.Modal(zipmodal, {});
    }catch (e){}

    modal.show();     // 모달창 띄우기
});

const showzipaddr = (jsons) => {
    jsons = JSON.parse(jsons); // 문자열을 json객체로 변환
    let addr = '';
    jsons.forEach(function (data,idx){ // josn 반복처리
        addr += `<option>${data['zipcode']} ${data['sido']} 
            ${data['gugun']} ${data['dong']} ${data['bunji']} </option>`;
    });
    addrlist.innerHTML = addr;
};
fzipbtn?.addEventListener('click',() =>{
   if (dong.value === '') {
       alert('동이름을 입력하세요!!');
       return; //안해도 나옴?
   }
   const url= '/join/zipcode?dong='+dong.value;
   fetch(url).then(response => response.text())
       // .then(text => console.log(text)) 콘솔창에 확인하는방법
       .then(text => showzipaddr(text))
});

sendzip?.addEventListener('click',()=>{
    let frm = document.forms.joinfrm;
    let addr = addrlist.value;  // 선택한 주소 항목
    if (addr !==''){
        // 123-456 서웅ㄹ 관악구 신림동
        let zip = addr.split(' ')[0];   //우편번호 추출
        let vaddr =
            `${addr.split(' ')[1]} ${addr.split(' ')[2]} ${addr.split(' ')[3]}`;  //주소 추출

        frm.zip1.value = zip.split('-')[0];
        frm.zip2.value = zip.split('-')[1];
        frm.addr1.value = vaddr;

        modal.hide();   // 모달창 닫음
    }else{
        alert('주소를 선택하세요!!');
    }
});

email3?.addEventListener('click', () => {
    let frm = document.forms.joinfrm;
    if (email3.value === '직접입력하기') {
        frm.email2.readOnly = false;
        frm.email2.value = '';
    } else if (email3.value !== '선택하세요') {
        frm.email2.readOnly = true;
        frm.email2.value = email3.value;
    }
});

// 우편번호 검색 엔터키 입력 차단
dong?.addEventListener('keydown',(e) => {
     if(e.keyCode === 13)        // 엔터키(13)가 입력되면
        e.preventDefault();     // 이벤트 전파 방지
});

//비밀번호 확인
let pwd = document.joinfrm.passwd;
let repwd = document.joinfrm.repasswd;
let pwdmsg = document.querySelector("#pwdmsg");

repwd?.addEventListener('blur',()=>{
    let pmsg = '비밀번호가 서로 일치하지 않습니다!!';
    pwdmsg.className = 'text-danger';

    if (pwd.value === repwd.value){
        pmsg = '비밀번호가 서로 일치합니다!!';
        pwdmsg.className = 'text-primary';
    }
    pwdmsg.innerText = pmsg;
});
// 아이디 중복검사
let userid = document.joinfrm.userid;
let checkuid = document.joinfrm.checkuid;
let uidmsg = document.querySelector("#uidmsg");

const styleCheckuid = (chkuid) => {
    let umsg = '사용 불가능한 아이디입니다!!';
    uidmsg.className = 'text-danger';
    checkuid.value = 'no';

    if (chkuid === '0'){
        umsg = '사용 가능한 아이디입니다!!';
        uidmsg.className = 'text-primary';
        checkuid.value = 'yes';
    }
    uidmsg.innerText = umsg;
};
userid?.addEventListener('blur',() =>{
    if (userid.value === '') {
        uidmsg.innerText = '6~16 자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다';
        uidmsg.className = 'text-warning';
        checkuid.value = 'no';
        return; //안해도 나옴?
    }
    const url= '/join/checkuid/'+userid.value;
    fetch(url).then(response => response.text())
        // .then(text => console.log(text)) 콘솔창에 확인하는방법
        .then(text => styleCheckuid(text))
});

