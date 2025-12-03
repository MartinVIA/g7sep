// Create a dropdown navbar menu
function createNavbar() {
    const navContainer = document.createElement('div');
    navContainer.className = 'navbar-container';
    
    // Create navbar bar
    const navBar = document.createElement('div');
    navBar.className = 'navbar';
    
    // Add logo to navbar
    const logo = document.createElement('img');
    logo.className = 'navbar-logo';
    logo.src = '../img/image_2025-12-03_152859823-removebg-preview.png';
    logo.alt = 'Cloverville Logo';
    
    const menuButton = document.createElement('button');
    menuButton.className = 'menu-button';
    menuButton.textContent = 'Menu';
    
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
    
    // Toggle dropdown on button click
    menuButton.addEventListener('click', () => {
        navList.classList.toggle('show');
    });
    
    // Close dropdown when a link is clicked
    navList.addEventListener('click', () => {
        navList.classList.remove('show');
    });
    
    // Add logo and menu button to navbar
    navBar.appendChild(logo);
    navBar.appendChild(menuButton);
    
    // Add navbar and dropdown menu to container
    navContainer.appendChild(navBar);
    navContainer.appendChild(navList);
    
    // Insert navbar at the beginning of body
    document.body.insertBefore(navContainer, document.body.firstChild);
}

// Call the function when DOM is ready
document.addEventListener('DOMContentLoaded', createNavbar);
