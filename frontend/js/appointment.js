document.getElementById("appointment-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const appointment = {
        doctorId: document.getElementById("doctorId").value,
        date: document.getElementById("date").value,
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
