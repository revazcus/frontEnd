document.addEventListener("DOMContentLoaded", function(event) {
    //HTTP Request Handler
    let api = {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Referer': null
        },

        getUsers: async () => await fetch("/admin/api/users",{method:"GET"}).then(res => res.json()),
        getUser: async id => await fetch(`/admin/api/users/${id}`, {method:"GET"}).then(res => res.json()),
        addUser: async obj => await fetch("/admin/api/users", {method:"POST", headers: api.headers, body: JSON.stringify(obj)}),
        editUser: async id => await fetch(`/admin/api/users/${id}`, {method:"PUT"}),
        deleteUser: async id => await fetch(`/admin/api/users/${id}`, {method:"DELETE"}),
        showUsers: items => {
            let usersTable = document.querySelector("#usersTable")
            items.forEach(item => {
                usersTable.innerHTML += `<tr id="${item.id}">
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.lastName}</td>
                <td>${item.age}</td>
                <td>${item.email}</td>
                <td>${item.role.toUpperCase()}</td>
                <td>
                <button class="btn btn-info editUser">Edit</button>
                </td>
                <td>
                <button class="btn btn-danger deleteUser">Delete</button>
                </td></tr>`
            })
        }

    };
    //Show users in table
    api.getUsers().then(res => api.showUsers(res))
    //Add Listeners
    document.addEventListener("click", async function (e){
        if (e.target.classList.contains("editUser")) {
            let uid = e.target.closest("tr").getAttribute("id");
            let user = await api.getUser(uid);
            document.querySelector('.userEditForm #editId').value = uid;
            document.querySelector('.userEditForm #name').value = user.name;
            document.querySelector('.userEditForm #lastName').value = user.lastName;
            document.querySelector('.userEditForm #age').value = user.age;
            document.querySelector('.userEditForm #email').value = user.email;
            document.querySelector('.userEditForm #password').value = user.password;
            document.querySelector(".userEditForm").style.display = "block"

        }
        if (e.target.classList.contains("deleteUser")) {
            //let uid = e.target.closest("tr").getAttribute("id"); console.log(uid)
        }
    });

    //Display input form
    document.querySelector("button.showUserForm").addEventListener("click", function (){
        document.querySelector(".topButtons.onFocus").classList.remove("onFocus");
        this.classList.add("onFocus");
        document.querySelector(".cardUserList").style.display = "none";
        document.querySelector(".cardAddUserForm").style.display = "flex";
    })

    document.querySelector("button.showUsers").addEventListener("click", function (){
        document.querySelector(".topButtons.onFocus").classList.remove("onFocus");
        this.classList.add("onFocus");
        document.querySelector(".cardUserList").style.display = "flex";
        document.querySelector(".cardAddUserForm").style.display = "none";
    })

    document.querySelector(".btn.addUser").addEventListener("click", async function (){
        let form = document.querySelector("#addUserForm");
        let formData = new FormData(form);
        let obj = Object.fromEntries(formData);
        obj.role = $(".roleSelect").val(); console.log(obj.role)
        api.addUser(obj).then(res => res.json()).then(res => console.log(res))
        //api.showUsers([obj])
    })









});


// findAllUsers: async () => await fetch('api/users'),
// findOneUser: async (id) => await fetch(`api/users/${id}`),
// addNewUser: async (user) => await fetch('api/users', {method: 'POST', headers: userFetchService.head, body: JSON.stringify(user)}),
// updateUser: async (user, id) => await fetch(`api/users/${id}`, {method: 'PUT', headers: userFetchService.head, body: JSON.stringify(user)}),
// deleteUser: async (id) => await fetch(`api/users/${id}`, {method: 'DELETE', headers: userFetchService.head})