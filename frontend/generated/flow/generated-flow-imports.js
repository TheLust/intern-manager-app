import '@vaadin/tooltip/theme/lumo/vaadin-tooltip.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/app-layout/theme/lumo/vaadin-drawer-toggle.js';
import '@vaadin/tabs/theme/lumo/vaadin-tab.js';
import '@vaadin/icons/vaadin-iconset.js';
import '@vaadin/icon/theme/lumo/vaadin-icon.js';
import '@vaadin/tabs/theme/lumo/vaadin-tabs.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/app-layout/theme/lumo/vaadin-app-layout.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '161621083f5f81a07226ebdac92ba0866b2551f856b4f468971ed3d16ae80291') {
    pending.push(import('./chunks/chunk-9ee97f1583517098f0d2f4d014b04e257e1f02b3197718b83862cbf1d1e96d12.js'));
  }
  if (key === 'b0d2b916ba09d6bb38419f2dc754bc221d6f3e7049bc510e7ceacd54303f333f') {
    pending.push(import('./chunks/chunk-9ee97f1583517098f0d2f4d014b04e257e1f02b3197718b83862cbf1d1e96d12.js'));
  }
  if (key === '63d8741ffe9e1c56b2cc44d9b50c392f4bcb5e4ae05d62ef0a7193c941239d47') {
    pending.push(import('./chunks/chunk-3e9dc72bde2fb981a99b202c31d134d143c9d17ad1a813062991aa336316e4e0.js'));
  }
  if (key === '86acafc55bfb28504c0c9c7d4caffa0323b1c93d7daff5e4e81249300900b3f9') {
    pending.push(import('./chunks/chunk-9ee97f1583517098f0d2f4d014b04e257e1f02b3197718b83862cbf1d1e96d12.js'));
  }
  if (key === '9356c9a73cbe21aecd65af6e6194a12d136c608e0f0ab3a5afa5ea9f309fad04') {
    pending.push(import('./chunks/chunk-9ee97f1583517098f0d2f4d014b04e257e1f02b3197718b83862cbf1d1e96d12.js'));
  }
  if (key === '8cc7d436a335b1780849b1b488db237adcb2da4a580bc9a073b35ae6ed06298a') {
    pending.push(import('./chunks/chunk-9ee97f1583517098f0d2f4d014b04e257e1f02b3197718b83862cbf1d1e96d12.js'));
  }
  if (key === '01fc3ff6633fa8f0cf95183bbbf77eccc850eaea9b9e3d0a338bcda0ca1957c7') {
    pending.push(import('./chunks/chunk-9ee97f1583517098f0d2f4d014b04e257e1f02b3197718b83862cbf1d1e96d12.js'));
  }
  if (key === 'f62f40fcfaa830527c04f3e5253df419d71ccaf8c7d21731bdb7587bb3bd7e3c') {
    pending.push(import('./chunks/chunk-9ee97f1583517098f0d2f4d014b04e257e1f02b3197718b83862cbf1d1e96d12.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;