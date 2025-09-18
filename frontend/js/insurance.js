document.getElementById("insurance-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const claim = {
        patientName: document.getElementById("patientName").value,
        policyNumber: document.getElementById("policyNumber").value,
        hospitalName: document.getElementById("hospitalName").value
    };

    try {
        const response = await fetch("http://localhost:8080/api/insurance", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(claim)
        });

        if (!response.ok) throw new Error("Failed to submit claim");

        alert("✅ Insurance claim submitted! Status: Pending");
        document.getElementById("insurance-form").reset();
    } catch (err) {
        console.error(err);
        alert("⚠️ Error connecting to server.");
    }
});
