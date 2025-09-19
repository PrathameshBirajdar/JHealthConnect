// Dynamic home page content loader
document.addEventListener('DOMContentLoaded', () => {
	// Only run on home page
	if (window.location.pathname.endsWith('index.html') || window.location.pathname === '/' || window.location.pathname === '/frontend/' ) {
		fetch('/api/home')
			.then(res => res.json())
			.then(data => {
				const header = document.querySelector('.header h1');
				const tagline = document.querySelector('.welcome-section h2');
				if (header && data.welcome) header.textContent = data.welcome;
				if (tagline && data.message) tagline.textContent = data.message;
			});
	}
});
