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
    console.log("loadPointsFromXML: loading", xmlPath);
    // If a generated JS data file exists (window.fileOperations), use it first.
    try {
        if (window.fileOperations && Array.isArray(window.fileOperations.personalPoints)) {
            console.log('Using in-page JS data (file_operations_data.js)');
            const pointsMap = {};
            for (const entry of window.fileOperations.personalPoints) {
                if (entry && entry.id != null) pointsMap[String(entry.id)] = String(entry.points);
            }
            // update status if available
            const statusEl = document.getElementById('points-status');
            if (statusEl && window.fileOperations && window.fileOperations.lastUpdated) {
                statusEl.textContent = 'Last updated: ' + window.fileOperations.lastUpdated;
            }
            displayPoints(pointsMap);
            return;
        }
    } catch (e) {
        console.warn('Error accessing window.fileOperations', e);
    }
    fetch(xmlPath, { cache: 'no-store' })
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
            return response.text();
        })
        .then(str => {
            console.log('Fetched XML length', str.length);
            const doc = (new window.DOMParser()).parseFromString(str, "text/xml");
            return doc;
        })
        .then(data => {
            try {
                const operations = data.getElementsByTagName("Operation");
                const pointsMap = {};
                for (let i = 0; i < operations.length; i++) {
                    const op = operations[i];
                    const type = op.getAttribute("type");
                    const fileAttr = op.getAttribute("file") || '';
                    if (type === "write" && fileAttr.indexOf("PersonalPoints") !== -1) {
                        const lines = op.getElementsByTagName("Line");
                        for (let j = 0; j < lines.length; j++) {
                            const text = lines[j].textContent.trim();
                            if (!text) continue;
                            // Accept "ID,Points" or "ID Points" formats
                            const parts = text.split(/[,;\s]+/).filter(Boolean);
                            if (parts.length >= 2) {
                                const id = parts[0];
                                const points = parts[1];
                                pointsMap[id] = points;
                            }
                        }
                    }
                }
                console.log('Parsed pointsMap', pointsMap);
                // if XML path used, update status
                const statusEl = document.getElementById('points-status');
                if (statusEl) {
                    // try to read lastUpdated from XML <Operation> attribute or leave blank
                    const op = (function(){
                        for (let i=0;i<operations.length;i++){
                            const o = operations[i];
                            if (o.getAttribute('file') && o.getAttribute('file').indexOf('PersonalPoints')!==-1) return o;
                        }
                        return null;
                    })();
                    if (op && op.getAttribute('timestamp')) {
                        statusEl.textContent = 'Last updated: ' + op.getAttribute('timestamp');
                    } else if (window.fileOperations && window.fileOperations.lastUpdated) {
                        statusEl.textContent = 'Last updated: ' + window.fileOperations.lastUpdated;
                    }
                }
                displayPoints(pointsMap);
            } catch (err) {
                console.error('Error parsing XML document', err);
                displayPoints({});
            }
        })
        .catch(err => {
            console.error('Failed to load XML', err);
            displayPoints({});
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
        loadPointsFromXML("../src/file_operations.xml");
    });
}
