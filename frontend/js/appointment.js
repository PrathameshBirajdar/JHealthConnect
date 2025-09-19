// Prefill doctor and doctor name if present in query params
document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const doctorId = params.get('doctorId');
    const doctorName = params.get('doctorName');
    const doctorSelect = document.getElementById('doctorId');
    if (doctorSelect) {
        // Fetch all doctors for the select
        try {
            const res = await fetch('http://127.0.0.1:8080/api/doctors');
            const doctors = await res.json();
            doctorSelect.innerHTML = '<option value="">Select</option>';
            for (const d of doctors) {
                const opt = document.createElement('option');
                opt.value = d.id;
                opt.textContent = d.name;
                if (doctorId && d.id == doctorId) {
                    opt.selected = true;
                }
                doctorSelect.appendChild(opt);
            }
        } catch (e) {
            doctorSelect.innerHTML = '<option value="">Error loading doctors</option>';
        }
    }
    // Optionally prefill patientName with doctorName (or show somewhere)
    // You can display doctorName above the form if desired
});
document.getElementById("appointment-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const appointment = {
        doctorId: document.getElementById("doctorId").value,
        date: document.getElementById("appointmentDate").value,
        patientName: document.getElementById("patientName").value,
        age: document.getElementById("age").value,
        gender: document.getElementById("gender").value,
        contact: document.getElementById("contact").value
    };

    try {
        const response = await fetch("http://localhost:8080/api/appointments", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(appointment)
        });

        if (!response.ok) throw new Error("Failed to book appointment");

        alert("✅ Appointment booked successfully!");
        document.getElementById("appointment-form").reset();
    } catch (err) {
        console.error(err);
        alert("⚠️ Error connecting to server.");
    }
});
