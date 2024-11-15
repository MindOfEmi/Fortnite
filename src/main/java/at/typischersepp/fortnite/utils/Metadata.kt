package at.typischersepp.fortnite.utils

import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

fun getMetadata(item: ItemStack, key: NamespacedKey, dataType: PersistentDataType<Int, Int>): Int {
    // Check if item has requested metadata
    if (!item.hasItemMeta()) throw Exception("Item doesn't have metadata")

    // Get metadata and return value
    val itemMeta = item.itemMeta
    val value = itemMeta.persistentDataContainer.get(key, dataType) ?: throw Exception("Key doesn't have any value")

    return value
}

fun setMetadata(item: ItemStack, key: NamespacedKey, dataType: PersistentDataType<Int, Int>, value: Int) {
    // Set new metadata
    val itemMeta = item.itemMeta
    item.itemMeta.persistentDataContainer.set(key, dataType, value)
    item.itemMeta = itemMeta
}