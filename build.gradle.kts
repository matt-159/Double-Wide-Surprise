plugins {
    id("com.falsepattern.fpgradle-mc") version ("0.16.1")
}

group = "matt159"

minecraft_fp {
    mod {
        modid = "dws"
        name = "Double Wide Surprise"
        rootPkg = "com.github.matt159.dws"
    }

    mixin {
        pkg = "mixin.mixins"
        pluginClass = "mixin.plugin.MixinPlugin"
    }
    core {
        accessTransformerFile = "dws_at.cfg"
    }

    tokens {
        tokenClass = "Tags"
    }

    publish {
        maven {
            repoUrl = "https://mvn.falsepattern.com/gtmega_releases/"
            repoName = "mega"
        }
    }
}

repositories {
    cursemavenEX()
    modrinthEX()
    ic2EX()
    exclusive(mavenpattern(), "com.falsepattern")
    exclusive(mega(), "codechicken", "team.chisel", "mega", "gtmega")
    exclusive(horizon(), "com.github.GTNewHorizons")
    exclusive(maven("usrv", "https://mvn.falsepattern.com/usrv"))
}

dependencies {

    // FalsePattern Lib
    implementation("com.falsepattern:falsepatternlib-mc1.7.10:1.6.1:dev")

    // Not Enough Items
    compileOnly("codechicken:codechickencore-mc1.7.10:1.4.0-mega:dev") {
        excludeDeps()
    }

    compileOnly("codechicken:notenoughitems-mc1.7.10:2.1.5-gtmega:dev") {
        excludeDeps()
    }

    // Galacticraft TODO: The @Mod annotation doesn't seem to be found when running as compile, however the core mod/mixins does load?
    compileOnly("com.github.GTNewHorizons:Galacticraft:3.0.56-GTNH:dev") {
        excludeDeps()
    }

    compileOnly(deobfCurse("agricraft-225635:2284130"))
    compileOnly(deobfCurse("cofh-core-69162:2388751"))
    compileOnly(deobfCurse("forestry-59751:2333823"))
    compileOnly(deobfCurse("garden-stuff-225903:2269692"))
    compileOnly(deobfCurse("iron-chests-228756:2230908"))
    compileOnly(deobfCurse("mantle-74924:2264244"))
    compileOnly(deobfCurse("mariculture-68023:2234977"))
    compileOnly(deobfCurse("natura-74120:2257670"))
    compileOnly(deobfCurse("storage-drawers-223852:2469586"))
    compileOnly(deobfCurse("tinkers-construct-74072:2264246"))
    compileOnly(deobfCurse("travellers-gear-224440:2262113"))
    compileOnly(deobfCurse("railcraft-51195:2458987"))
    compileOnly(deobfCurse("industrial-craft-242638:2353971"))
    compileOnly(deobfCurse("thaumcraft-223628:2227552"))
    compileOnly(deobfCurse("thaumcraft-nei-plugin-225095:2241913"))
    compileOnly(deobfCurse("forge-backpacks-59143:2268883"))
    implementation(deobfModrinth("baubles-expanded:2.2.1"))

    // GregTech
    compileOnly("gtmega:gt5u-mc1.7.10:5.38.0-mega:dev") {
        excludeDeps()
    }

    compileOnly("com.github.GTNewHorizons:inventory-tweaks:1.5.13:dev")
    compileOnly("com.github.GTNewHorizons:EnderCore:0.2.9:dev") {
        excludeDeps()
    }

    // GregTech Addons
    compileOnly("mega:tectech-mc1.7.10:4.11.0:dev") {
        excludeDeps()
    }

    compileOnly("gtmega:ae2-mc1.7.10:rv3-beta.58-gtmega:dev") {
        excludeDeps()
    }
    compileOnly("team.chisel:chisel-mc1.7.10:2.9.20-mega:dev") {
        excludeDeps()
    }
}
