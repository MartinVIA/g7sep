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

// --- Personal Points JSON Loader ---
function loadPointsFromJSON(jsonPath) {
    console.log("loadPointsFromJSON: loading", jsonPath);
    
    // Try to construct the correct path - go up one level from docs/
    let actualPath = jsonPath;
    
    // If running from file:// protocol, try to build the correct path
    if (window.location.protocol === 'file:') {
        const docsDir = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
        const projectRoot = docsDir.substring(0, docsDir.lastIndexOf('/'));
        actualPath = projectRoot + '/file_operations_personalpoints.json';
        console.log("File protocol detected. Using path:", actualPath);
    }
    
    fetch(actualPath, { cache: 'no-store' })
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
            return response.json();
        })
        .then(data => {
            console.log('Fetched JSON data', data);
            const pointsMap = {};
            // data should be an object mapping id -> points
            for (const [id, points] of Object.entries(data)) {
                pointsMap[String(id)] = String(points);
            }
            console.log('Parsed pointsMap', pointsMap);
            // Update status with current time
            const statusEl = document.getElementById('points-status');
            if (statusEl) {
                const now = new Date().toISOString();
                statusEl.textContent = 'Last updated: ' + now;
            }
            displayPoints(pointsMap);
        })
        .catch(err => {
            console.error('Failed to load JSON from', actualPath, err);
            // Try alternative path if the first one fails
            if (actualPath !== jsonPath) {
                console.log('Retrying with original path:', jsonPath);
                fetch(jsonPath, { cache: 'no-store' })
                    .then(r => r.ok ? r.json() : Promise.reject('Not ok'))
                    .then(data => {
                        const pointsMap = {};
                        for (const [id, points] of Object.entries(data)) {
                            pointsMap[String(id)] = String(points);
                        }
                        displayPoints(pointsMap);
                    })
                    .catch(e => {
                        console.error('Fallback also failed:', e);
                        displayPoints({});
                    });
            } else {
                displayPoints({});
            }
        });
}

function displayPoints(pointsMap) {
    const container = document.getElementById("points-list");
    if (!container) return;
    container.innerHTML = "";
    const entries = Object.entries(pointsMap || {});
    if (entries.length === 0) {
        const empty = document.createElement('div');
        empty.className = 'points-empty';
        empty.textContent = 'No personal points data available.';
        container.appendChild(empty);
        return;
    }
    for (const [id, points] of entries) {
        const item = document.createElement("div");
        item.className = "points-item";
        item.textContent = `ID: ${id} - Points: ${points}`;
        container.appendChild(item);
    }
}

// Only run on personalpoints.html
if (window.location.pathname.includes('personalpoints.html')) {
    document.addEventListener("DOMContentLoaded", function() {
        loadPointsFromJSON("../file_operations_personalpoints.json");
    });
}

// --- Tasks JSON Loader ---
function loadTasksFromJSON(jsonPath) {
    console.log("loadTasksFromJSON: loading", jsonPath);
    
    let actualPath = jsonPath;
    if (window.location.protocol === 'file:') {
        const docsDir = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
        const projectRoot = docsDir.substring(0, docsDir.lastIndexOf('/'));
        actualPath = projectRoot + '/file_operations_tasks.json';
        console.log("File protocol detected. Using path:", actualPath);
    }
    
    fetch(actualPath, { cache: 'no-store' })
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
            return response.json();
        })
        .then(data => {
            console.log('Fetched tasks JSON data', data);
            displayTasks(data || []);
        })
        .catch(err => {
            console.error('Failed to load tasks JSON', err);
            displayTasks([]);
        });
}

function displayTasks(tasksArray) {
    const container = document.getElementById("tasks-list");
    if (!container) return;
    container.innerHTML = "";
    if (!tasksArray || tasksArray.length === 0) {
        const empty = document.createElement('div');
        empty.className = 'tasks-empty';
        empty.textContent = 'No tasks data available.';
        container.appendChild(empty);
        return;
    }
    for (const task of tasksArray) {
        const item = document.createElement("div");
        item.className = "task-item";
        item.textContent = `Task: ${task.name} (Type: ${task.type})`;
        container.appendChild(item);
    }
}

// Only run on tasks.html
if (window.location.pathname.includes('tasks.html')) {
    document.addEventListener("DOMContentLoaded", function() {
        loadTasksFromJSON("../file_operations_tasks.json");
    });
}

// --- Trades JSON Loader ---
function loadTradesFromJSON(jsonPath) {
    console.log("loadTradesFromJSON: loading", jsonPath);
    
    let actualPath = jsonPath;
    if (window.location.protocol === 'file:') {
        const docsDir = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
        const projectRoot = docsDir.substring(0, docsDir.lastIndexOf('/'));
        actualPath = projectRoot + '/file_operations_trades.json';
        console.log("File protocol detected. Using path:", actualPath);
    }
    
    fetch(actualPath, { cache: 'no-store' })
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
            return response.json();
        })
        .then(data => {
            console.log('Fetched trades JSON data', data);
            displayTrades(data || []);
        })
        .catch(err => {
            console.error('Failed to load trades JSON', err);
            displayTrades([]);
        });
}

function displayTrades(tradesArray) {
    const container = document.getElementById("trades-list");
    if (!container) return;
    container.innerHTML = "";
    if (!tradesArray || tradesArray.length === 0) {
        const empty = document.createElement('div');
        empty.className = 'trades-empty';
        empty.textContent = 'No trades data available.';
        container.appendChild(empty);
        return;
    }
    for (const trade of tradesArray) {
        const item = document.createElement("div");
        item.className = "trade-item";
        item.textContent = `Trade: ${trade.name || 'N/A'}`;
        container.appendChild(item);
    }
}

// Only run on trades.html
if (window.location.pathname.includes('trades.html')) {
    document.addEventListener("DOMContentLoaded", function() {
        loadTradesFromJSON("../file_operations_trades.json");
    });
}
