document.addEventListener("DOMContentLoaded", () => {

	    const menuBtn = document.getElementById("menuBtn");

	    if(menuBtn){
	        menuBtn.addEventListener("click", () => {

	            document
	                .getElementById("sidebar")
	                ?.classList
	                .toggle("show");

	            document
	                .getElementById("overlay")
	                ?.classList
	                .toggle("show");
	        });
	    }
		
		const overlay = document.getElementById("overlay");

		overlay?.addEventListener("click", () => {

		    document
		        .getElementById("sidebar")
		        ?.classList
		        .remove("show");

		    overlay.classList.remove("show");
		});

	function confirmLogout(event) {

	    event.preventDefault();

	    const sidebar = document.getElementById("sidebar");
	    const overlay = document.getElementById("overlay");

	    sidebar?.classList.remove("show");
	    overlay?.classList.remove("show");

	    Swal.fire({
	        title: '¿Cerrar sesión?',
	        text: 'Tendrás que volver a iniciar sesión',
	        showCancelButton: true,
	        confirmButtonColor: '#dc2626',
	        cancelButtonColor: '#64748b',
	        confirmButtonText: 'Sí, cerrar sesión',
	        cancelButtonText: 'Cancelar'
	    }).then((result) => {

	        if (result.isConfirmed) {
	            window.location.href = '/logout';
	        } else {
	            sidebar?.classList.add("show");
	            overlay?.classList.add("show");
	        }
	    });
	}
	window.confirmLogout = confirmLogout;
	
	const toggle = document.getElementById("togglePassword");
	const input = document.getElementById("password");

	if (toggle && input) {

	    toggle.onclick = () => {

	        const show = input.type === "password";

	        input.type = show ? "text" : "password";

	        toggle.classList.toggle("fa-eye");
	        toggle.classList.toggle("fa-eye-slash");
	    };
	}

	document.querySelectorAll(".eliminar-btn").forEach(btn => {

	    btn.addEventListener("click", function(e) {

	        e.preventDefault();

	        const url = this.href;

	        Swal.fire({
	            title: '¿Eliminar usuario?',
	            text: 'El usuario será eliminado permanentemente.',
	            showCancelButton: true,
	            confirmButtonText: 'Eliminar',
	            cancelButtonText: 'Cancelar',
	            confirmButtonColor: '#dc2626',
	            reverseButtons: true,
				
	            customClass: {
	                popup: 'swal-modern'
	            }

	        }).then((result) => {
	            if (result.isConfirmed) {
	                window.location.href = url;
	            }
	        });
	    });
	});


	document.querySelectorAll(".btn-editar").forEach(btn => {

	    btn.addEventListener("click", () => {

	        document.getElementById("editId").value = btn.dataset.id;
	        document.getElementById("editNombre").value = btn.dataset.nombre;
	        document.getElementById("editCorreo").value = btn.dataset.correo;
	        document.getElementById("editRol").value = btn.dataset.rol;

	        const selectRol = document.getElementById("editRol");

	        if (btn.dataset.propio === "true") {
	            selectRol.disabled = true;
	        } else {
	            selectRol.disabled = false;
	        }
	    });
	});
	
	const formEditarUsuario = document.getElementById("formEditarUsuario");

	if(formEditarUsuario){

	    formEditarUsuario.addEventListener("submit", function(e){

	        e.preventDefault();

	        Swal.fire({
	            title: "¿Guardar cambios?",
	            text: "Se actualizará la información del usuario.",
	            icon: "question",
	            showCancelButton: true,
	            confirmButtonText: "Actualizar",
	            cancelButtonText: "Cancelar",
	            confirmButtonColor: "#2563eb"

	        }).then((result)=>{

	            if(result.isConfirmed){
	                formEditarUsuario.submit();
	            }
	        });
	    });
	}

	document.querySelectorAll(".eliminar-tarea").forEach(btn => {
	    btn.addEventListener("click", function(e) {

	        e.preventDefault();

	        const url = this.href;

	        Swal.fire({
	            title: '¿Eliminar tarea?',
	            text: 'Esta acción no se puede revertir.',
	            icon: 'warning',
	            showCancelButton: true,
	            confirmButtonText: 'Eliminar',
	            cancelButtonText: 'Cancelar',
	            confirmButtonColor: '#dc2626',
	            reverseButtons: true

	        }).then(result => {

	            if (result.isConfirmed) {
	                window.location.href = url;
	            }
	        });
	    });
	});

	document.querySelectorAll(".btn-editar-tarea").forEach(btn => {

	    btn.addEventListener("click", () => {

	        document.getElementById("editIdTarea").value = btn.dataset.id;
	        document.getElementById("editTitulo").value = btn.dataset.titulo;
	        document.getElementById("editDescripcion").value = btn.dataset.descripcion;
	        document.getElementById("editFecha").value = btn.dataset.fecha;
			document.getElementById("editEstado").value = btn.dataset.estado;
			document.getElementById("editPrioridad").value = btn.dataset.prioridad;
			document.getElementById("editUsuario").value = btn.dataset.usuario;
			document.getElementById("editProyecto").value = btn.dataset.proyecto;
	    });
	});
	
	document.querySelectorAll(".btn-editar-proyecto").forEach(btn => {

	    btn.addEventListener("click", () => {

	        document.getElementById("editIdProyecto").value = btn.dataset.id;
	        document.getElementById("editNombreProyecto").value = btn.dataset.nombre;
	        document.getElementById("editDescripcionProyecto").value = btn.dataset.descripcion;
	        document.getElementById("editFechaInicioProyecto").value = btn.dataset.inicio;
	        document.getElementById("editFechaFinProyecto").value = btn.dataset.fin;
	    });
	});
	
	document
	.querySelectorAll(".eliminar-proyecto")
	.forEach(btn => {

	    btn.addEventListener("click", function(e) {
	        e.preventDefault();

	        const url = this.href;

	        Swal.fire({
	            title: "¿Eliminar proyecto?",
	            text: "Esta acción no se puede revertir.",
	            icon: "warning",
	            showCancelButton: true,
	            confirmButtonText: "Eliminar",
	            cancelButtonText: "Cancelar",
	            confirmButtonColor: "#dc2626"

	        }).then((result) => {
	            if(result.isConfirmed){

	                window.location.href = url;
	            }
	        });
	    });
	});
	
	
	function toggleNavDropdown(panelId, wrapperId) {
	    const panel = document.getElementById(panelId);
	    const isOpen = panel.classList.contains('open');
	    // Cerrar todos
	    document.querySelectorAll('.nav-dropdown-panel').forEach(p => p.classList.remove('open'));
	    if (!isOpen) panel.classList.add('open');
	}
	document.addEventListener('click', function(e) {
	    if (!e.target.closest('.nav-dropdown-wrapper')) {
	        document.querySelectorAll('.nav-dropdown-panel').forEach(p => p.classList.remove('open'));
	    }
	});

	// Cargar notificaciones desde el backend
	function cargarNotificaciones() {
	    fetch('/api/notificaciones/mis')
	        .then(r => r.ok ? r.json() : [])
	        .then(data => {
	            const list = document.getElementById('notifList');
	            const badge = document.getElementById('notifBadge');
	            if (!data || data.length === 0) {
	                list.innerHTML = `<div class="nav-panel-empty"><i class="fa-regular fa-bell-slash"></i><span>Sin notificaciones</span></div>`;
	                badge.style.display = 'none';
	                return;
	            }
	            const noLeidas = data.filter(n => !n.leido).length;
	            badge.textContent = noLeidas > 9 ? '9+' : noLeidas;
	            badge.style.display = noLeidas > 0 ? 'flex' : 'none';
	            list.innerHTML = data.map(n => `
	                <div class="nav-notif-item ${!n.leido ? 'unread' : ''}" onclick="marcarLeida(${n.idNotificacion}, this)">
	                    <div class="nav-notif-dot" style="${n.leido ? 'background:rgba(255,255,255,0.2)' : ''}"></div>
	                    <div>
	                        <div class="nav-notif-text">${n.mensaje}</div>
	                        <div class="nav-notif-time">${formatFechaRelativa(n.fecha)}</div>
	                    </div>
	                </div>`).join('');
	        }).catch(() => {});
	}

	// Cargar mensajes recientes (últimos comentarios en tareas del usuario)
	function cargarMensajes() {
	    fetch('/api/mensajes/recientes')
	        .then(r => r.ok ? r.json() : [])
	        .then(data => {
	            const list = document.getElementById('msgList');
	            const badge = document.getElementById('msgBadge');
	            if (!data || data.length === 0) {
	                list.innerHTML = `<div class="nav-panel-empty"><i class="fa-regular fa-envelope-open"></i><span>No hay mensajes nuevos</span></div>`;
	                badge.style.display = 'none';
	                return;
	            }
	            badge.textContent = data.length > 9 ? '9+' : data.length;
	            badge.style.display = 'flex';
	            list.innerHTML = data.map(m => `
	                <div class="nav-msg-item">
	                    <div class="nav-msg-avatar">${m.usuarioNombre ? m.usuarioNombre.charAt(0).toUpperCase() : '?'}</div>
	                    <div class="nav-msg-content">
	                        <div class="nav-msg-sender">${m.usuarioNombre || 'Usuario'}</div>
	                        <div class="nav-msg-preview">${m.mensaje}</div>
	                        <div class="nav-msg-task"><i class="fa-solid fa-tag"></i> ${m.tareaTitulo || ''}</div>
	                    </div>
	                </div>`).join('');
	        }).catch(() => {});
	}

	function marcarLeida(id, el) {
	    fetch(`/api/notificaciones/${id}/leer`, { method: 'POST' })
	        .then(() => {
	            el.classList.remove('unread');
	            el.querySelector('.nav-notif-dot').style.background = 'rgba(255,255,255,0.2)';
	            cargarNotificaciones();
	        }).catch(() => {});
	}

	function marcarTodasLeidas() {
	    fetch('/api/notificaciones/leer-todas', { method: 'POST' })
	        .then(() => cargarNotificaciones()).catch(() => {});
	}

	function formatFechaRelativa(fechaStr) {
	    if (!fechaStr) return '';
	    const fecha = new Date(fechaStr);
	    const ahora = new Date();
	    const diff = Math.floor((ahora - fecha) / 60000);
	    if (diff < 1) return 'Ahora mismo';
	    if (diff < 60) return `Hace ${diff} min`;
	    if (diff < 1440) return `Hace ${Math.floor(diff/60)} h`;
	    return `Hace ${Math.floor(diff/1440)} d`;
	}

	document.addEventListener('DOMContentLoaded', function() {
	    cargarNotificaciones();
	    cargarMensajes();
	    // Actualizar cada 60 segundos
	    setInterval(cargarNotificaciones, 60000);
	    setInterval(cargarMensajes, 60000);
	});
	
	// Estadísticas de perfil
	 const statTareas = document.getElementById('statTareas');
	 if (statTareas) {
	     const usuarioId = document.getElementById('usuarioIdData')?.dataset.id || 0;
	     fetch(`/api/tareas/usuario/${usuarioId}`)
	         .then(r => r.ok ? r.json() : [])
	         .then(tareas => {
	             document.getElementById('statTareas').textContent      = tareas.length;
	             document.getElementById('statCompletadas').textContent = tareas.filter(t => t.estado === 'FINALIZADO').length;
	             document.getElementById('statPendientes').textContent  = tareas.filter(t => t.estado === 'PENDIENTE').length;
	         })
	         .catch(() => {
	             document.getElementById('statTareas').textContent      = '—';
	             document.getElementById('statCompletadas').textContent = '—';
	             document.getElementById('statPendientes').textContent  = '—';
	         });
	 }
	 
	 /* Pega al final del DOMContentLoaded en app.js */

	     // confirmarFinalizacion — botón de finalizar tarea en el workspace
	     function confirmarFinalizacion(btn) {
	         const url = btn.dataset.url;
	         Swal.fire({
	             title: '¿Finalizar tarea?',
	             text: 'Esta acción marcará la tarea como completada.',
	             icon: 'question',
	             showCancelButton: true,
	             confirmButtonText: 'Sí, finalizar',
	             cancelButtonText: 'Cancelar'
	         }).then((result) => {
	             if (result.isConfirmed) window.location.href = url;
	         });
	     }
	     window.confirmarFinalizacion = confirmarFinalizacion;

	     // Modal de detalle + comentarios
	     const detalleModal = document.getElementById('detalleModal');
	     if (detalleModal) {
	         let tareaIdActual = null;

	         const userInitialEl = document.getElementById('userInitial');
	         const userNameEl    = document.getElementById('userNameData');
	         if (userInitialEl && userNameEl) {
	             userInitialEl.textContent = (userNameEl.dataset.nombre || 'U').charAt(0).toUpperCase();
	         }

	         detalleModal.addEventListener('show.bs.modal', function(event) {
	             const btn = event.relatedTarget;
	             tareaIdActual = btn.getAttribute('data-tarea-id');

	             document.getElementById('mTitulo').textContent      = btn.getAttribute('data-titulo');
	             document.getElementById('mDescripcion').textContent = btn.getAttribute('data-descripcion');
	             document.getElementById('mProyecto').textContent    = btn.getAttribute('data-proyecto');
	             document.getElementById('mFecha').textContent       = btn.getAttribute('data-fecha');

	             const estado    = btn.getAttribute('data-estado');
	             const prioridad = btn.getAttribute('data-prioridad');
	             const eBadge    = document.getElementById('mEstadoBadge');
	             const pBadge    = document.getElementById('mPrioridadBadge');

	             const estadoColors = {
	                 PENDIENTE:  'background:#64748b;color:#fff',
	                 EN_PROCESO: 'background:#f59e0b;color:#000',
	                 FINALIZADO: 'background:#22c55e;color:#fff'
	             };
	             const prioColors = {
	                 ALTA:  'background:#ef4444;color:#fff',
	                 MEDIA: 'background:#f59e0b;color:#000',
	                 BAJA:  'background:#06b6d4;color:#000'
	             };

	             eBadge.style.cssText = estadoColors[estado]    || 'background:#64748b;color:#fff';
	             eBadge.textContent   = estado ? estado.replace('_', ' ') : '';
	             pBadge.style.cssText = prioColors[prioridad]   || 'background:#64748b;color:#fff';
	             pBadge.textContent   = prioridad || '';

	             const tabDetalles = document.getElementById('tab-detalles');
	             if (tabDetalles) bootstrap.Tab.getOrCreateInstance(tabDetalles).show();

	             cargarComentarios(tareaIdActual);
	         });

	         function cargarComentarios(idTarea) {
	             const list = document.getElementById('comentariosList');
	             list.innerHTML = '<div class="comment-loading"><i class="fa-solid fa-spinner fa-spin"></i> Cargando...</div>';
	             fetch(`/comentarios/tarea/${idTarea}`)
	                 .then(r => r.json())
	                 .then(data => {
	                     document.getElementById('commentCount').textContent = data.length;
	                     if (data.length === 0) {
	                         list.innerHTML = '<div class="comment-empty"><i class="fa-regular fa-comments"></i>Sé el primero en comentar</div>';
	                         return;
	                     }
	                     list.innerHTML = data.map(c => `
	                         <div class="comment-item-new">
	                             <div class="comment-avatar-item">${c.usuario?.nombre?.charAt(0).toUpperCase() || '?'}</div>
	                             <div class="comment-bubble">
	                                 <div class="comment-bubble-name">${c.usuario?.nombre || 'Usuario'}</div>
	                                 <div class="comment-bubble-text">${c.mensaje}</div>
	                                 <div class="comment-bubble-time">${formatearFecha(c.fechaComentario)}</div>
	                             </div>
	                         </div>`).join('');
	                     list.scrollTop = list.scrollHeight;
	                 })
	                 .catch(() => {
	                     list.innerHTML = '<div class="comment-empty"><i class="fa-solid fa-triangle-exclamation"></i>Error al cargar comentarios</div>';
	                 });
	         }

	         function enviarComentario() {
	             const textarea = document.getElementById('nuevoComentario');
	             const btn      = document.getElementById('btnEnviarComentario');
	             const texto    = textarea.value.trim();
	             if (!texto || !tareaIdActual) return;
	             btn.disabled = true;
	             btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Enviando...';
	             const form = new FormData();
	             form.append('mensaje', texto);
	             form.append('idTarea', tareaIdActual);
	             fetch('/comentarios', { method: 'POST', body: form })
	                 .then(r => { if (!r.ok) throw new Error(); return r.json(); })
	                 .then(() => { textarea.value = ''; cargarComentarios(tareaIdActual); })
	                 .catch(() => alert('No se pudo enviar el comentario.'))
	                 .finally(() => {
	                     btn.disabled = false;
	                     btn.innerHTML = '<i class="fa-solid fa-paper-plane"></i> Comentar';
	                 });
	         }
	         window.enviarComentario = enviarComentario;

	         document.getElementById('nuevoComentario')?.addEventListener('keydown', e => {
	             if (e.ctrlKey && e.key === 'Enter') enviarComentario();
	         });

	         function formatearFecha(isoStr) {
	             if (!isoStr) return '';
	             const d    = new Date(isoStr);
	             const diff = Math.floor((new Date() - d) / 60000);
	             if (diff < 1)    return 'Ahora mismo';
	             if (diff < 60)   return `Hace ${diff} min`;
	             if (diff < 1440) return `Hace ${Math.floor(diff / 60)} h`;
	             return d.toLocaleDateString('es-PE', { day: '2-digit', month: 'short', year: 'numeric' });
	         }
	     }
	
	
});
