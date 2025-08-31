<template>
  <section>
    <div>
      <h2>No More Pop-ups</h2>
      <p>A Minecraft mod to remove in-game pop-ups. No More Pop-ups removes:</p>
      <ul>
        <li>Removes "New Recipe Unlocked!" pop-ups</li>
        <li>Remove Tutorial pop-ups</li>
        <li>Remove Advancement pop-ups</li>
        <li>Remove resource pack warning</li>
        <li>Remove multiplayer warning</li>
        <li>Remove experimental world warning</li>
        <li>Remove advancements messages in chat (disabled by default)</li>
      </ul>
      <p>All these pop-ups can be configured!.</p>
      <p>
        This mod is available for Minecraft 1.20 and above.<br />
        This mod is only available for Fabric (0.16.4)
      </p>
    </div>
    <img src="../assets/images/icon.png" alt="No More Pop-ups logo" />
  </section>
</template>

<style scoped>
@media screen and (max-width: 1024px) {
  section > img {
    display: none;
  }
}

section > img {
  height: 200px;
  filter: drop-shadow(0 0 5px #00000080);
  image-rendering: pixelated;
}
</style>
