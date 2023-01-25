let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{ // ()=>{} 사용이유 : this를 바인딩하기 위해서!
            this.save();
        }); // 누군가 btnsave를
        /*$("#btn-login").on("click", ()=>{ // ()=>{} 사용이유 : this를 바인딩하기 위해서!
            this.login();
        });*/
    },

   /* login: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        };

        //console.log(data);
        // ajax 호출시 default가 비동기 호출
        $.ajax({
            //회원가입 요청중에도 아래 default(fail)가 실행중이게 됨
            type: "POST",
            url: "/api/login",
            data: JSON.stringify(data), //http body data
            contentType: "application/json; charset=utf-8", // body data가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든게 string 으로 들어옴(생긴게 json인 것임 => javascript 오브젝트로 변경)
            //  ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        }).done(function(resp){ //resp에 입력값들이 포함된 json이 들어옴
            alert("로그인이 완료되었습니다.");
            location.href ="/";
            console.log(resp);
        }).fail(function(errer){
            alert(JSON.stringify(error));
        }); // ajax 통신을 통하여 3개의 파라미터 데이터를 json으로 변경하여 insert 요청을 할 것임
        // 통신 수행 -> 응답의 결과가 정상이면 done 실행, 실패하면 fail 실행

    },*/
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };

        //console.log(data);
        // ajax 호출시 default가 비동기 호출
        $.ajax({
            //회원가입 요청중에도 아래 default(fail)가 실행중이게 됨
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), //http body data
            contentType: "application/json; charset=utf-8", // body data가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든게 string 으로 들어옴(생긴게 json인 것임 => javascript 오브젝트로 변경)
            //  ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        }).done(function(resp){ //resp에 입력값들이 포함된 json이 들어옴
            alert("회원가입이 완료되었습니다.");
            location.href ="/";
            console.log(resp);
        }).fail(function(errer){
            alert(JSON.stringify(error));
        }); // ajax 통신을 통하여 3개의 파라미터 데이터를 json으로 변경하여 insert 요청을 할 것임
        // 통신 수행 -> 응답의 결과가 정상이면 done 실행, 실패하면 fail 실행

    }
}

index.init();