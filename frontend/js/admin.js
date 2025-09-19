// Admin doctor add logic
const API_BASE = 'http://127.0.0.1:8080/api';

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('add-doctor-form');
    const msg = document.getElementById('doctor-msg');
    if (!form) return;
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        msg.textContent = '';
        msg.className = '';
        const doctor = {
            name: form.doctorName.value,
            specialization: form.specialization.value,
            location: form.location.value,
            photoUrl: form.photoUrl.value
        };
        try {
            const res = await fetch(`${API_BASE}/doctors`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(doctor)
            });
            if (!res.ok) throw new Error('Failed to add doctor');
            form.reset();
            msg.textContent = '✅ Doctor added successfully!';
            msg.className = 'success-msg';
        } catch (err) {
            msg.textContent = '❌ ' + (err.message || 'Error adding doctor');
            msg.className = 'error-msg';
        }
    });
});
