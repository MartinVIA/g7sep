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
    logo.src = 'img/clovervilleLogo.png';
    logo.alt = 'Cloverville Logo';
    
    // Check if on home page
    const isHomePage = window.location.pathname.includes('index.html') || window.location.pathname.endsWith('/docs/') || window.location.pathname.endsWith('/docs');
    
    // Get page title based on current page
    let pageTitle = 'CloverVille';
    if (window.location.pathname.includes('tasks.html')) {
        pageTitle = 'Tasks';
    } else if (window.location.pathname.includes('trades.html')) {
        pageTitle = 'Trades';
    } else if (window.location.pathname.includes('personalpoints.html')) {
        pageTitle = 'Personal Points';
    }
    
    if (isHomePage) {
        logo.className = 'navbar-logo navbar-logo-center';
    } else {
        logo.className = 'navbar-logo navbar-logo-left';
        logo.style.cursor = 'pointer';
        logo.addEventListener('click', () => {
            window.location.href = 'index.html';
        });
    }
    
    // Create page title element for non-home pages
    const pageNameElement = document.createElement('div');
    pageNameElement.className = 'navbar-page-name';
    pageNameElement.textContent = pageTitle;
    
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
    if (!isHomePage) {
        navBar.appendChild(pageNameElement);
    }
    navBar.appendChild(menuButton);
    
    // Add navbar and dropdown menu to container
    navContainer.appendChild(navBar);
    navContainer.appendChild(navList);
    
    // Insert navbar at the beginning of body
    document.body.insertBefore(navContainer, document.body.firstChild);
}

// Call the function when DOM is ready
document.addEventListener('DOMContentLoaded', createNavbar);

// --- Personal Points XML Loader ---
function loadPointsFromXML(xmlPath) {
    fetch(xmlPath)
        .then(response => response.text())
        .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
        .then(data => {
            const operations = data.getElementsByTagName("Operation");
            const pointsMap = {};
            for (let op of operations) {
                if (op.getAttribute("type") === "write" && op.getAttribute("file").includes("PersonalPoints")) {
                    const lines = op.getElementsByTagName("Line");
                    for (let line of lines) {
                        // Assuming line format: ID,Points
                        const [id, points] = line.textContent.split(/[,;\s]+/);
                        if (id && points) {
                            pointsMap[id] = points;
                        }
                    }
                }
            }
            displayPoints(pointsMap);
        });
}

function displayPoints(pointsMap) {
    const container = document.getElementById("points-list");
    if (!container) return;
    container.innerHTML = "";
    for (const [id, points] of Object.entries(pointsMap)) {
        const item = document.createElement("div");
        item.className = "points-item";
        item.textContent = `ID: ${id} - Points: ${points}`;
        container.appendChild(item);
    }
}

// Only run on personalpoints.html
if (window.location.pathname.includes('personalpoints.html')) {
    document.addEventListener("DOMContentLoaded", function() {
        loadPointsFromXML("../src/file_operations.xml");
    });
}
