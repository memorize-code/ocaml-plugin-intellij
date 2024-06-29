package com.dune.sdk.api

import java.util.*

enum class DuneCommand {
    BUILD, CACHE, CLEAN, COQ,
    DESCRIBE, DIAGNOSTICS, EXEC,
    EXTERNAL_LIB_DEPS, FMT, FORMAT_DUNE_FILE,
    HELP, INIT, INSTALL, INSTALLED_LIBRARIES,
    INTERNAL, MONITOR, OCAML, OCAML_MERLIN, PKG,
    PRINTENV, PROMOTE, PROMOTION, RPC, RULES,
    RUNTEST, SHOW, SHUTDOWN, SUBST, TEST,
    TOP, UNINSTALL, UPGRADE, UTOP;

    val value = name.lowercase(Locale.getDefault())
}