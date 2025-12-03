// Create a nice navbar dynamically
function createNavbar() {
    const navbar = document.createElement('nav');
    navbar.className = 'navbar';
    
    const navList = document.createElement('ul');
    navList.className = 'nav-list';
    
    const navItems = [
        { text: 'Home', href: 'index.html' },
        { text: 'Tasks', href: 'tasks.html' },
        { text: 'Trades', href: 'trades.html' },
        { text: 'Personal Points', href: 'personalpoints.html' }
    ];
    
    navItems.forEach(item => {
        const li = document.createElement('li');
        const a = document.createElement('a');
        a.textContent = item.text;
        a.href = item.href;
        a.className = 'nav-link';
        
        // Add active state to current page
        if (window.location.pathname.includes(item.href)) {
            a.classList.add('active');
        }
        
        li.appendChild(a);
        navList.appendChild(li);
    });
    
    navbar.appendChild(navList);
    
    // Insert navbar at the beginning of body
    document.body.insertBefore(navbar, document.body.firstChild);
}

// Call the function when DOM is ready
document.addEventListener('DOMContentLoaded', createNavbar);
