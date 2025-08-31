<script setup>
import WikiNav from './WikiNav.vue'
import { onMounted } from 'vue'

onMounted(() => {
  const nav = document.querySelector('nav')
  const main = document.querySelector('main')
  if (nav && main) {
    main.style.paddingTop = nav.offsetHeight + 'px'
  }
})
</script>

<template>
  <main>
    <WikiNav />
    <section class="wiki-content">
      <h1>How to install No More Pop-ups</h1>
      <p>
        You can install the No More Pop-ups mod using either <strong>Modrinth</strong> or
        <strong>CurseForge</strong>. Both methods are easy and safe!
      </p>
      <ol>
        <li>
          <strong>Modrinth (recommended):</strong>
          <ul>
            <li>
              Go to the
              <a href="https://modrinth.com/project/sAdB6HVW" target="_blank" rel="noopener"
                >No More Pop-ups Modrinth page</a
              >.
            </li>
            <li>Click the download button</li>
            <li>Select the appropriate Minecraft version</li>
            <li>
              Place the <code>.jar</code> into your mods directory (make sure you're using
              Fabric-API mod)
            </li>
            <li>Enjoy the mod!</li>
          </ul>
        </li>
        <li>
          <strong>CurseForge:</strong>
          <ul>
            <li>
              Go to the
              <a
                href="https://www.curseforge.com/minecraft/mc-mods/no-more-popups"
                target="_blank"
                rel="noopener"
                >No More Pop-ups CurseForge page</a
              >.
            </li>
            <li>Click the download button</li>
            <li>Select the appropriate Minecraft version</li>
            <li>
              Place the <code>.jar</code> into your mods directory (make sure you're using
              Fabric-API mod)
            </li>
            <li>Enjoy the mod!</li>
          </ul>
        </li>
      </ol>
      <p>
        <strong>Need help?</strong> If you have trouble installing, check the documentation on
        Modrinth/CurseForge or ask for help on the
        <a href="https://discord.gg/TcmUQHZ5U4" target="_blank" rel="noopener"
          >No More Pop-ups Discord server</a
        >.
      </p>
    </section>
  </main>
</template>

<style scoped>
main {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  width: 100vw;
  max-width: 100vw;
  box-sizing: border-box;
}

@media (max-width: 1024px) {
  main {
    flex-direction: column;
  }
}
</style>
