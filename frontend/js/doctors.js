document.addEventListener("DOMContentLoaded", () => {
    fetch("http://localhost:8080/api/doctors")
        .then(response => response.json())
        .then(data => {
            const list = document.getElementById("doctor-list");
            list.innerHTML = "";

            data.forEach(doc => {
                const card = document.createElement("div");
                card.className = "doctor-card";
                card.innerHTML = `
          <img src="assets/doctor${doc.id}.jpg" alt="${doc.name}" />
          <h3>${doc.name}</h3>
          <p><b>Specialization:</b> ${doc.specialization}</p>
          <p><b>Location:</b> ${doc.location}</p>
        `;
                list.appendChild(card);
            });
        })
        .catch(err => {
            console.error("Error fetching doctors:", err);
            alert("⚠️ Could not load doctors.");
        });
});
