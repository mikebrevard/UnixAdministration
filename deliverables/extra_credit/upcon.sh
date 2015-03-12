#!/bin/bash
IFS=$'\n' vm_names=(`vagrant status --machine-readable | awk '{FS=","} { print $2 }' | uniq`)

for vm in "${vm_names[@]}"; do
  echo "[$vm] Bringing up VM"

  vagrant up $vm&
done

# Make sure all child processes have finished before exiting.
wait

echo "VMs are up"
