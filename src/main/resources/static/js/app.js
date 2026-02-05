const api = {
  get: (u) => fetch(u).then(async r => { if(!r.ok) throw new Error(await r.text()); return r.json(); }),
  post: (u, b) => fetch(u, {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(b)}).then(async r=>{if(!r.ok) throw new Error((await r.text())||'Request failed'); return r.json();})
};

const store = {
  get token(){return localStorage.getItem('lc_token')||''},
  set token(v){localStorage.setItem('lc_token', v||'')},
  get currentUser(){return localStorage.getItem('lc_current_user')||''},
  set currentUser(v){localStorage.setItem('lc_current_user', v||'')}
};

async function refreshUsers(selectId){
  const users = await api.get('/api/users');
  document.querySelectorAll('select.user-select').forEach(sel=>{
    sel.innerHTML = '<option value="">Select user</option>' + users.map(u=>`<option value="${u.id}">${u.displayName}</option>`).join('');
    if (selectId) sel.value = selectId;
  });
}

function showMsg(id, msg, bad=false){
  const el = document.getElementById(id); if(!el) return;
  el.textContent = msg; el.style.color = bad ? '#ff9bb0' : '#8ff7dd';
}

function requireAuth(){
  if (!store.token) {
    window.location.href = '/auth.html';
  }
}

function logout(){
  store.token = '';
  store.currentUser = '';
  window.location.href = '/auth.html';
}
