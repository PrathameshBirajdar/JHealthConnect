// Admin patient add logic
const API_BASE = 'http://127.0.0.1:8080/api';

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('add-patient-form');
    const msg = document.getElementById('patient-msg');
    if (!form) return;
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        msg.textContent = '';
        msg.className = '';
        const patient = {
            name: form.patientName.value,
            age: form.age.value,
            gender: form.gender.value,
            contact: form.contact.value
        };
        try {
            const res = await fetch(`${API_BASE}/patients`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(patient)
            });
            if (!res.ok) throw new Error('Failed to add patient');
            form.reset();
            msg.textContent = '✅ Patient added successfully!';
            msg.className = 'success-msg';
        } catch (err) {
            msg.textContent = '❌ ' + (err.message || 'Error adding patient');
            msg.className = 'error-msg';
        }
    });
});
