self.addEventListener('install', (e) => {
    e.waitUntil(
      caches.open('gymApp-store').then((cache) => cache.addAll([
        '/public/',
        '/public/index.html'
      ])),
    );
  });
  
  self.addEventListener('fetch', (e) => {
    console.log(e.request.url);
    e.respondWith(
      caches.match(e.request).then((response) => response || fetch(e.request)),
    );
  });