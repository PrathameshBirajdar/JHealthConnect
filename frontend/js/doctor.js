document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/doctors')
        .then(res => res.json())
        .then(doctors => {
            const list = document.getElementById('doctor-list');
            list.innerHTML = '';
            doctors.forEach(doc => {
                const card = document.createElement('div');
                card.className = 'doctor-card';
                card.innerHTML = `
                    <div class="doctor-name">${doc.name}</div>
                    <div class="doctor-specialty">${doc.specialty}</div>
                    <div class="doctor-contact">Contact: ${doc.contact || 'N/A'}</div>
                `;
                list.appendChild(card);
            });
        })
        .catch(() => {
            document.getElementById('doctor-list').innerHTML = '<p>Could not load doctors.</p>';
        });
});