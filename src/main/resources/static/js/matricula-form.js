
// ====== utilidades de mapeo ======
function makePairs(fromPrefix, toPrefix) {
  const base = [
    ['_rut','_rut'],
    ['_nombres','_nombres'],
    ['_apPat','_apPat'],
    ['_apMat','_apMat'],
    ['_nac','_nac'],
    ['_fnac','_fnac'],
    ['_dir','_dir'],
    ['_comuna','_comuna'],
    ['_tel1','_tel1'],
    ['_tel2','_tel2']
  ];
  // correo existe en padre/madre y en titular (en suplente puede faltar según tu HTML)
  base.push(['_correo','_correo']);

  // nivel y ocupación solo existen en padre/madre; no se copian desde titular/suplente
  return base.map(([a,b]) => [`${fromPrefix}${a}`, `${toPrefix}${b}`]);
}

function getEl(id){ return document.getElementById(id); }
function copyValue(fromId, toId, override=false) {
  const from = getEl(fromId), to = getEl(toId);
  if (!from || !to) return;
  if (override || !to.value) {
    to.value = from.value;
    to.dispatchEvent(new Event('input', {bubbles:true}));
    to.dispatchEvent(new Event('change', {bubbles:true}));
  }
}

// ====== gestor de sincronización (añadir/quitar listeners) ======
const syncRegistry = new Map(); // key=checkboxId, value=array de {fromId, toId, handler}

function startSync(checkboxId, pairs) {
  // copia inicial
  pairs.forEach(([fromId, toId]) => copyValue(fromId, toId, true));

  // listeners en vivo
  const entries = [];
  pairs.forEach(([fromId, toId]) => {
    const from = getEl(fromId);
    if (!from) return;
    const handler = () => copyValue(fromId, toId, true);
    from.addEventListener('input', handler);
    from.addEventListener('change', handler);
    entries.push({fromId, toId, handler});
  });
  syncRegistry.set(checkboxId, entries);
}

function stopSync(checkboxId) {
  const entries = syncRegistry.get(checkboxId) || [];
  entries.forEach(({fromId, handler}) => {
    const from = getEl(fromId);
    if (from) {
      from.removeEventListener('input', handler);
      from.removeEventListener('change', handler);
    }
  });
  syncRegistry.delete(checkboxId);
}

// ====== setup por bloque (Titular/Suplente -> Padre/Madre) ======
function setupBlock(options) {
  const {
    fromPrefix,                 // 'tit' o 'sup'
    padreCheckboxId,            // ej. 'tit_es_padre'
    madreCheckboxId,            // ej. 'tit_es_madre'
    toPadrePrefix='pad',        // destino padre
    toMadrePrefix='mad'         // destino madre
  } = options;

  const chkPadre = getEl(padreCheckboxId);
  const chkMadre = getEl(madreCheckboxId);

  function onPadreChange() {
    if (!chkPadre) return;
    if (chkPadre.checked) {
      // exclusión mutua
      if (chkMadre) { chkMadre.checked = false; stopSync(madreCheckboxId); }
      startSync(padreCheckboxId, makePairs(fromPrefix, toPadrePrefix));
    } else {
      stopSync(padreCheckboxId);
    }
  }

  function onMadreChange() {
    if (!chkMadre) return;
    if (chkMadre.checked) {
      if (chkPadre) { chkPadre.checked = false; stopSync(padreCheckboxId); }
      startSync(madreCheckboxId, makePairs(fromPrefix, toMadrePrefix));
    } else {
      stopSync(madreCheckboxId);
    }
  }

  if (chkPadre) chkPadre.addEventListener('change', onPadreChange);
  if (chkMadre) chkMadre.addEventListener('change', onMadreChange);

  // si al cargar ya vienen marcados (re-hidratación)
  if (chkPadre && chkPadre.checked) onPadreChange();
  if (chkMadre && chkMadre.checked) onMadreChange();
}

// ====== inicialización ======
document.addEventListener('DOMContentLoaded', () => {
  // TITULAR -> PADRE/MADRE
  setupBlock({
    fromPrefix: 'tit',
    padreCheckboxId: 'tit_es_padre',
    madreCheckboxId: 'tit_es_madre'
  });

  // SUPLENTE -> PADRE/MADRE
  setupBlock({
    fromPrefix: 'sup',
    padreCheckboxId: 'sup_es_padre',
    madreCheckboxId: 'sup_es_madre'
  });
});

