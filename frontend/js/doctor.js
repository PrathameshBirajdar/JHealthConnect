const API_BASE = 'http://127.0.0.1:8080/api';

function el(tag, attrs = {}, ...children) {
    const node = document.createElement(tag);
    for (const [k, v] of Object.entries(attrs)) {
        if (k === 'class') node.className = v;
        else if (k === 'html') node.innerHTML = v;
        else node.setAttribute(k, v);
    }
    for (const c of children) {
        if (typeof c === 'string') node.appendChild(document.createTextNode(c));
        else if (c) node.appendChild(c);
    }
    return node;
}

async function fetchDoctors() {
    const list = document.getElementById('doctor-list');
    list.innerHTML = '';

    const loader = el('div', {class: 'doctor-card', style: 'display:flex;align-items:center;justify-content:center;height:200px;min-width:320px;'}, 'Loading...');
    list.appendChild(loader);

    try {
        const res = await fetch(`${API_BASE}/doctors`, { credentials: 'omit' });
        if (!res.ok) throw new Error(`Server returned ${res.status}`);
        const data = await res.json();

        list.innerHTML = '';
        if (!Array.isArray(data) || data.length === 0) {
            list.appendChild(el('div', {class: 'doctor-card'}, el('div', {class: 'doctor-info', html: '<div class="doctor-name">No doctors found</div>'}))); 
            return;
        }

        for (const d of data) {
            const placeholder = 'https://via.placeholder.com/640x360?text=Doctor';
            const img = el('img', {class: 'doctor-img', src: d.photoUrl || placeholder, alt: d.name || 'Doctor'});
            const info = el('div', {class: 'doctor-info'},
                el('div', {class: 'doctor-name'}, d.name || ''),
                el('div', {class: 'doctor-specialty'}, d.specialization || ''),
                el('div', {class: 'doctor-location'}, d.location || ''),
                el('button', {
                    class: 'book-btn',
                    type: 'button',
                    onclick: () => {
                        window.location.href = `/appointment.html?doctorId=${encodeURIComponent(d.id)}&doctorName=${encodeURIComponent(d.name)}`;
                    }
                }, 'Book')
            );

            const card = el('div', {class: 'doctor-card'}, img, info);
            list.appendChild(card);
        }

    } catch (err) {
        list.innerHTML = '';
        list.appendChild(el('div', {class: 'doctor-card'}, el('div', {class: 'doctor-info', html: `<div class="doctor-name">Error loading doctors</div><div style="color:#666;margin-top:8px">${err.message}</div>`}))); 
        console.error('Failed to fetch doctors', err);
    }
}

window.addEventListener('load', () => {
    fetchDoctors();
});