(() => {
    // --- Œil (délégation : fonctionne partout, y compris contenu injecté) ---
    document.addEventListener('click', (e) => {
        const btn = e.target.closest('[data-toggle-password]');
        if (!btn) return;
        const input = document.getElementById(btn.dataset.togglePassword);
        if (!input) return;

        const show = input.type === 'password';
        input.type = show ? 'text' : 'password';
        btn.querySelector('.material-symbols-outlined').textContent = show ? 'visibility_off' : 'visibility';
        btn.setAttribute('aria-pressed', String(show));
        btn.setAttribute('aria-label', show ? 'Masquer le mot de passe' : 'Afficher le mot de passe');
    });

    // --- Règles et niveaux ---
    const tests = {
        length: (v) => v.length >= 8,
        upper: (v) => /[A-Z]/.test(v),
        lower: (v) => /[a-z]/.test(v),
        symbol: (v) => /[^A-Za-z0-9]/.test(v),
    };

    const levels = [
        { text: 'Saisissez un mot de passe', segs: 0, color: 'text-on-surface-variant' },
        { text: 'Faible (1/3)', segs: 1, color: 'text-error' },
        { text: 'Faible (1/3)', segs: 1, color: 'text-error' },
        { text: 'Moyen (2/3)', segs: 2, color: 'text-secondary' },
        { text: 'Fort (3/3)', segs: 3, color: 'text-tertiary-container' },
    ];
    const labelColors = [...new Set(levels.map((l) => l.color))];

    function initGroup(group) {
        const pwd = group.querySelector('[data-pw="password"]');
        const confirm = group.querySelector('[data-pw="confirm"]');   // null au login
        const strength = group.querySelector('[data-pw-strength]');    // null si désactivé
        if (!pwd) return;

        // Jauge + checklist (seulement si présentes)
        let refreshStrength = () => { };
        if (strength) {
            const label = strength.querySelector('[data-strength-label]');
            const segments = strength.querySelectorAll('[data-strength-bar] > div');
            const rules = strength.querySelectorAll('[data-rule]');

            refreshStrength = () => {
                let passed = 0;
                rules.forEach((row) => {
                    const ok = tests[row.dataset.rule](pwd.value);
                    if (ok) passed++;
                    row.querySelector('.material-symbols-outlined').textContent =
                        ok ? 'check_circle' : 'radio_button_unchecked';
                    row.classList.toggle('text-tertiary-container', ok);
                    row.classList.toggle('text-outline', !ok);
                });

                const level = levels[passed];
                segments.forEach((seg, i) => {
                    const active = i < level.segs;
                    seg.classList.toggle('bg-secondary', active);
                    seg.classList.toggle('bg-surface-container-highest', !active);
                });
                label.textContent = level.text;
                label.classList.remove(...labelColors);
                label.classList.add(level.color);
            };
        }

        // Confirmation (seulement si présente)
        let checkMatch = () => { };
        if (confirm) {
            checkMatch = () => {
                const mismatch = confirm.value.length > 0 && confirm.value !== pwd.value;
                confirm.classList.toggle('ring-2', mismatch);
                confirm.classList.toggle('ring-error', mismatch);
                confirm.setCustomValidity(mismatch ? 'Les mots de passe ne correspondent pas.' : '');
                confirm.setAttribute('aria-invalid', String(mismatch));
            };
            confirm.addEventListener('input', checkMatch);
        }

        pwd.addEventListener('input', () => { refreshStrength(); checkMatch(); });
        refreshStrength();
        checkMatch();
    }

    const init = () => document.querySelectorAll('[data-password-group]').forEach(initGroup);
    document.readyState === 'loading' ? document.addEventListener('DOMContentLoaded', init) : init();
})();