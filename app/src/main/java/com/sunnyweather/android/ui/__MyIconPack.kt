package com.sunnyweather.android.ui

import androidx.compose.ui.graphics.vector.ImageVector
import Alarm
import com.sunnyweather.android.ui.myiconpack.Image
import com.sunnyweather.android.ui.myiconpack.Leaf
import com.sunnyweather.android.ui.myiconpack.LetterT
import com.sunnyweather.android.ui.myiconpack.PencilLine
import com.sunnyweather.android.ui.myiconpack.Radioupnp
import com.sunnyweather.android.ui.myiconpack.RedoLine
import com.sunnyweather.android.ui.myiconpack.TShirt
import com.sunnyweather.android.ui.myiconpack.UndoLine
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Alarm, Image, Leaf, LetterT, PencilLine, Radioupnp, RedoLine, TShirt,
        UndoLine)
    return __AllIcons!!
  }
