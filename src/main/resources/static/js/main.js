$(document).ready(function (){

    $('button.editUser').on('click', function (event){
        event.preventDefault();
        let uid = $(this).parents("tr").attr("id");
        let url = `admin/findUser/?id=${uid}`;

        $.get(url, function (user, status){
            $('.myForm #editId').val(uid);
            $('.myForm #name').val(user.name);
            $('.myForm #lastName').val(user.lastName);
            $('.myForm #age').val(user.age);
            $('.myForm #email').val(user.email);
            $('.myForm #password').val(user.password);
        })
        $('#exampleModal').modal();
    })

    $('button.deleteUser').on('click', function (event){
        event.preventDefault();
        let uid = $(this).parents("tr").attr("id");
        let url = `admin/findUser/?id=${uid}`;

        $.get(url, function (user, status){ console.log(user);
            $('.modalDelete #deleteId').val(uid);
            $('.modalDelete #nameDelete').val(user.name);
            $('.modalDelete #lastNameDelete').val(user.lastName);
            $('.modalDelete #ageDelete').val(user.age);
            $('.modalDelete #emailDelete').val(user.email);
            $('.modalDelete #passwordDelete').val(user.password);
        });

        $('#exampleModalDelete').modal();
    })

    // $('button.confirmDelete').on('click', function (event){
    //     event.preventDefault();
    //     let uid = $(this).parents("#exampleModalDelete").find("#deleteId").val();
    //     let url = `admin/deleteUser/?id=${uid}`;
    //     $.post(url, function (){});
    // })
});