const api = {
  get: (u) => fetch(u).then(r => r.json()),
  post: (u, b) => fetch(u, {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(b)}).then(async r=>{if(!r.ok) throw new Error((await r.text())||'Request failed'); return r.json();})
};

const store = {
  get users(){return JSON.parse(localStorage.getItem('lc_users')||'[]')},
  set users(v){localStorage.setItem('lc_users',JSON.stringify(v))},
  get selectedUser(){return localStorage.getItem('lc_selected_user')||''},
  set selectedUser(v){localStorage.setItem('lc_selected_user',v||'')}
};

async function refreshUsers(selectId){
  const users = await api.get('/api/users');
  store.users = users;
  document.querySelectorAll('select.user-select').forEach(sel=>{
    sel.innerHTML = '<option value="">Select user</option>' + users.map(u=>`<option value="${u.id}">${u.displayName}</option>`).join('');
    if (selectId) sel.value = selectId;
  });
}

function showMsg(id, msg, bad=false){
  const el = document.getElementById(id); if(!el) return;
  el.textContent = msg; el.style.color = bad ? '#ff9bb0' : '#8ff7dd';
}
