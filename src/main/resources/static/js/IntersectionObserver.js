"use strict";

const io = new IntersectionObserver((entries, observer) => {
    entries.forEach(entry => {
        if (!entry.isIntersecting) return;
        if (page._scrollchk) return;

        observer.observe(document.getElementById('fetchMore'));
        page._page += 1;
        page.list.search();
    });
});

io.observe(document.getElementById('fetchMore'));